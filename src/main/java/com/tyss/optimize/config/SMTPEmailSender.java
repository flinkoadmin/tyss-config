package com.tyss.optimize.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SMTPEmailSender {
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE = "spring.mail.properties.mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
    public static final String SPRING_MAIL_SSL_ENABLE = "spring.mail.ssl.enable";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH = "spring.mail.properties.mail.smtp.auth";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String SPRING_MAIL_PROPERTIES_MAIL_TRANSPORT_PROTOCOL = "spring.mail.properties.mail.transport.protocol";
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String SPRING_MAIL_HOST = "spring.mail.host";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String SPRING_MAIL_PORT = "spring.mail.port";
    public static final String MAIL_SMTP_SSL_PROTOCOLS = "mail.smtp.ssl.protocols";
    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_SSL_PROTOCOLS = "spring.mail.properties.mail.smtp.ssl.protocols";
    @Autowired
    private Environment env;

    public Properties emailConfig(){
        Properties properties = new Properties();

        properties.put(MAIL_SMTP_STARTTLS_ENABLE, env.getProperty(SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE));
        properties.put(MAIL_SMTP_SSL_ENABLE, env.getProperty(SPRING_MAIL_SSL_ENABLE));
        properties.put(MAIL_SMTP_AUTH, env.getProperty(SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH));
        properties.put(MAIL_TRANSPORT_PROTOCOL, env.getProperty(SPRING_MAIL_PROPERTIES_MAIL_TRANSPORT_PROTOCOL));
        properties.put(MAIL_SMTP_HOST, env.getProperty(SPRING_MAIL_HOST));
        properties.put(MAIL_SMTP_PORT, env.getProperty(SPRING_MAIL_PORT));
        properties.put(MAIL_SMTP_SSL_PROTOCOLS, env.getProperty(SPRING_MAIL_PROPERTIES_MAIL_SMTP_SSL_PROTOCOLS));
        return properties;
    }
}
