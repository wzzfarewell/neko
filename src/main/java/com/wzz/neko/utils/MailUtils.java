package com.wzz.neko.utils;

import com.wzz.neko.dto.MailVo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * MailUtils
 * 发送邮件的工具类
 * @author wzzap
 * @date 2019/7/9
 **/
public class MailUtils {

    private Properties props;
    private Authenticator auth;

    private MailUtils(){
        props = new Properties();
        //属性mail.smtp.auth设置发送时是否校验用户名和密码
        //属性mail.transport.protocol设置要使用的邮件协议
        //属性mail.host表示发送服务器的邮件服务器地址
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");
        auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nekofarewell@foxmail.com", "ydredzdosjjobijc");
            }
        };
    }

    private static volatile MailUtils instance = null;

    public static MailUtils getInstance(){
        if(instance == null){
            synchronized (MailUtils.class){
                if(instance == null){
                    instance = new MailUtils();
                }
            }
        }
        return instance;
    }

    public void sendMail(MailVo mailVo) throws MessagingException {
        Session session = Session.getInstance(props, auth);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("nekofarewell@foxmail.com"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailVo.getToEmail()));
        message.setSubject("用户激活");
        message.setContent(mailVo.getContent(), "text/html;charset=utf-8");
        Transport.send(message);
    }
}
