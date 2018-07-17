package com.summerzhou.email;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    private static Logger logger = Logger.getLogger(EmailUtils.class);
    /**
     * 发送邮件
     * @param info
     * @return
     */
    public static boolean sendEmail(EmailInfo info) {
        //设置邮件内容
        try {
            Message message = getMessage(info);
            message.setText(info.getMailContent());
            Transport.send(message);
            logger.info("邮件发送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 构建message
     * @param info
     * @return
     */
    private static Message getMessage(EmailInfo info) throws Exception {
        EmailAuthenticator emailAuthenticator = null;
        Properties properties = info.getProperties();
        //进行验证
        if(info.isAuto()){
            emailAuthenticator = new EmailAuthenticator(info.getUsername(),info.getPassword());
        }
        //构建session
        Session session = Session.getDefaultInstance(properties,emailAuthenticator);
        //创建Message
        MimeMessage message = new MimeMessage(session);
        //设置邮件的发送者地址和邮件显示的发送者名字
        message.setFrom(new InternetAddress(info.getFromAddress(),info.getFromUserName()));
        String toAddress = info.getToAddress();
        //设置邮件的收件者
        if(StringUtils.isNotBlank(toAddress)) {
            if (toAddress.contains(",")) {
                //如果收件者有多个
                message.setRecipients(Message.RecipientType.TO, toAddress);
            } else {
                //收件者只有一个
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }
        }
        //设置邮件的抄送者
        String ccAddress = info.getCcAddress();
        if(StringUtils.isNotBlank(ccAddress)){
            if(ccAddress.contains(",")){
                //有多个抄送者
                message.setRecipients(Message.RecipientType.CC,ccAddress);
            }else {
                //一个抄送者
                message.setRecipient(Message.RecipientType.CC,new InternetAddress(ccAddress));
            }
        }
        //设置邮件的主题
        message.setSubject(info.getMailTitle());
        message.setSentDate(new Date());
        return message;
    }
}
