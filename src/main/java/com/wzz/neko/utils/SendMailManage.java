package com.wzz.neko.utils;

import com.wzz.neko.dto.MailVo;
import com.wzz.neko.exceptions.MailNotExistException;
import com.wzz.neko.exceptions.SendMailException;
import com.wzz.neko.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * SendMailManage
 *
 * @author wzzfarewell
 * @date 2020/2/24
 **/
@Slf4j
@Component
public class SendMailManage{
    private static MailService mailService;

    @Autowired
    public void setMailService(MailService mailService){
        SendMailManage.mailService = mailService;
    }
    /**
     *    缓存发送邮件的队列
     */
    public static BlockingQueue<MailVo> queue = new ArrayBlockingQueue<>(2000);
    /**
     *    发送邮件线程状态
     */
    private static boolean isRunning = false;
    /**
     *    超时时间间隔 3分钟
     */
    public static long TIMEOUT_INTERVAL = 3 * 60 * 1000;

    public static boolean sendMail(MailVo mail){
        boolean rsp = false;
        try {
            //阻塞3秒
            rsp = queue.offer(mail, 3, TimeUnit.SECONDS);
            if(!rsp){
                throw new SendMailException("服务器发送邮件繁忙，请稍后再发");
            }
            if(!isRunning){
                startup();
                isRunning = true;
            }
        } catch (Exception e) {
            log.error("添加发送邮件队列出错", e);
        }
        return rsp;
    }

    public static void startup(){
        if(isRunning){
            return;
        }
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(() -> {
            log.debug("邮件发送消息线程启动");
            long timestamp = System.currentTimeMillis();
            while(isRunning) {
                long timeout = timestamp + TIMEOUT_INTERVAL;
                if(System.currentTimeMillis()>timeout) {
                    //空跑一段时间(3分钟)后线程退出
                    break;
                }
                try {
                    if(queue.size()==0) {
                        Thread.sleep(1000);
                        continue;
                    }
                    timestamp = System.currentTimeMillis();
                    MailVo mail = queue.poll();
                    mailService.sendRegisterMail(mail);

                    //发送一个邮件休息2秒，防止发送过快，导致主邮箱被锁定
                    Thread.sleep(2000);
                } catch (Exception e) {
                    log.error("邮件推送线程出错",e);
                    throw new MailNotExistException();
                }
            }
            isRunning = false;
            log.debug("邮件发送线程停止。");
        });
    }

}
