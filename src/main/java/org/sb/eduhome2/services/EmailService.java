package org.sb.eduhome2.services;

public interface EmailService {
    void sendConfirmationEmail(String email, String token);

}