package com.wzz.neko.service;

import com.wzz.neko.config.MailProperties;
import com.wzz.neko.core.Const;
import com.wzz.neko.dto.MailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
public class MailService {

    private final JavaMailSender javaMailSender;

    private final MailProperties mailProperties;

    @Autowired
    public MailService(JavaMailSender javaMailSender, MailProperties mailProperties) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    public void sendRegisterMail(MailVo mailVo){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getProperties().get("from"));
            helper.setTo(mailVo.getToEmail());
            helper.setSubject(mailVo.getSubject());
            helper.setText(mailVo.getContent(), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送邮件给[{}]失败", mailVo.getToEmail());
        }

    }

    /**
     * 发送带附件的邮件
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param file 附件
     */
    public void sendAttachFileMail(String from, String to, String cc, String subject, String content, File file){
        try{
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content);
            helper.addAttachment(file.getName(), file);
            javaMailSender.send(mimeMailMessage);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
    /**
     * 发送带图片的邮件
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param srcPath 图片资源路径
     * @param resIds  图片资源ID
     */
    public void sendMailWithImg(String from, String to, String cc, String subject, String content, String[] srcPath, String[] resIds){
        if(srcPath.length != resIds.length){
            System.out.println("发送失败！");
            return;
        }
        try{
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            // 设置正文为html格式
            helper.setText(content, true);
            for(int i = 0; i < srcPath.length; i++){
                // 构造静态资源
                FileSystemResource res = new FileSystemResource(new File(srcPath[i]));
                // 将资源加入到邮件对象
                helper.addInline(resIds[i], res);
            }
            javaMailSender.send(mimeMailMessage);
        }catch (MessagingException e){
            System.out.println("发送失败！");
        }
    }

}
