package com.sff.rbacdemo.common.utils;

import com.sff.rbacdemo.common.model.MailBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Frankie Fan
 * @date 2022-04-29 14:45
 */
@Slf4j
@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    /**
     * 发送邮件测试方法
     */
    public void sendMail(MailBean mailBean) {
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom(mailProperties.getUsername());
        mimeMessage.setTo(mailBean.getToAccount());
        mimeMessage.setSubject(mailBean.getSubject());
        mimeMessage.setText(mailBean.getContent());
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件-附件邮件
     *
     * @param mailBean
     */
    public boolean sendMailAttachment(MailBean mailBean) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(mailBean.getToAccount());
            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getContent(), true);
            log.info(mailBean.getAttachmentFileName());
            helper.addAttachment(mailBean.getAttachmentFileName(), mailBean.getAttachmentFile());
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
