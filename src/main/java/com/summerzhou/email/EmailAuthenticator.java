package com.summerzhou.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
    String userName;
    String userPassword;

    public EmailAuthenticator() {
        super();
    }

    public EmailAuthenticator(String user, String pwd) {
        super();
        userName = user;
        userPassword = pwd;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, userPassword);
    }

}
