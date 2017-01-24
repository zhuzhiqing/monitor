package com.jason.trade.util;

import com.jason.trade.common.OpResult;
import com.jason.trade.enums.OpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangliang17 on 16/11/16.
 */
//@Component
public class EmailUtils {

    private static final String REG_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

    private static String MAIL_SMTP_HOST = "smtp.163.com";
    public static final Integer MAIL_SMTP_PORT = Integer.valueOf(25);
    public static final Integer MAIL_TIME_OUT = Integer.valueOf(5000);
    private static String MAIL_MASTER = "zhiqing_zhu@163.com";
    private static String MAIL_MASTER_PWD = "";

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);

    @Value("${mail_smtp_host}")
    private String mailSmtpHost;

    @Value("${username}")
    private String mailMaster;

    @Value("${password}")
    private String mailMasterPwd;


//    @PostConstruct
//    public void init() {
//        MAIL_SMTP_HOST = mailSmtpHost;
//        MAIL_MASTER = mailMaster;
//        MAIL_MASTER_PWD = mailMasterPwd;
//    }

    public EmailUtils() {
    }

    public static OpResult sendEmail(String emailTitle, List<String> toUserList, String emailContent) {
        return sendEmail(emailTitle, toUserList, emailContent, true);
    }

    public static OpResult sendEmail(String emailTitle, List<String> toUserList, String emailContent, boolean isHtmlMail) {
        Address[] tos = validMailAddress(toUserList);

        if (MAIL_SMTP_PORT > 0) {
            if (null != tos && tos.length != 0) {
                try {
                    MimeMessage e = new MimeMessage(sessionAuth(MAIL_MASTER, MAIL_MASTER_PWD, MAIL_SMTP_HOST, MAIL_SMTP_PORT, MAIL_TIME_OUT, true));
                    e.setSubject(emailTitle);
                    if (isHtmlMail) {
                        e.setContent(emailContent, "text/html;charset=utf-8");
                    } else {
                        e.setText(emailContent);
                    }

                    e.setFrom(new InternetAddress(MAIL_MASTER));
                    e.setRecipients(Message.RecipientType.TO, tos);
                    e.setSentDate(new Date());
                    Transport.send(e);
                    return OpResult.createSucRst();
                } catch (MessagingException e) {
//                    errorLogger.error("alarm mail send error", e);
                    return OpResult.createFailRst(OpCodeEnum.FAILED_INTERNAL);
                }

            }
        }

        return OpResult.createFailRst(OpCodeEnum.FAILED_INTERNAL);
    }

    public static Address[] validMailAddress(List<String> multiMailAddress) {
        try {
            if (null != multiMailAddress && multiMailAddress.size() > 0) {
                InternetAddress[] tos = new InternetAddress[multiMailAddress.size()];

                for (int i = 0; i < multiMailAddress.size(); ++i) {
                    String receiver = (String) multiMailAddress.get(i);
                    if (isEmail(receiver)) {
                        tos[i] = new InternetAddress(receiver);
                    }
                }

                return tos;
            }
        } catch (Exception e) {
//            errorLogger.error("validMailAddress error", e);
        }

        return new Address[0];
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", 2);
        Matcher matcher = pattern.matcher(email);
        return StringUtils.isNotBlank(email) && matcher.matches();
    }

    public static Session sessionAuth(String username, String password, String smtpHost, int smtpPort, int timeout, boolean isAuth) {
        EmailUtils.PopupAuthenticator auth = null;
        if (isAuth) {
            auth = new PopupAuthenticator(username, password);
        }

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", isAuth ? "true" : "false");
        props.setProperty("mail.transport.protocol", "auth");
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.smtp.port", String.valueOf(smtpPort));
        Session session = Session.getDefaultInstance(props, auth);
        return session;
    }

    static class PopupAuthenticator extends Authenticator {
        private String username = null;
        private String password = null;

        public PopupAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.username, this.password);
        }

    }

//    public static void main(String [] args) {
//        sendEmail("来信回复", Lists.newArrayList("tozhuzhiqing@hotmail.com"),"你好，您的报名邮件已收到");
//    }
}

