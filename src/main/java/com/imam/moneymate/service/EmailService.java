package com.imam.moneymate.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendEmail(String to, String subject, String body);

    public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filName) throws MessagingException;
}
