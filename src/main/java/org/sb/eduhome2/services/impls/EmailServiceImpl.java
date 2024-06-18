package org.sb.eduhome2.services.impls;

import org.sb.eduhome2.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendConfirmationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sharifov.murad29@gmail.com");
        message.setTo(email);
        message.setSubject("Confirm email");
        // http://localhost:9090/auth/confrim?email=rizvan@itbrain.edu.az&token=adfhaskjfhaj
        // Template literals
        String res = "http://localhost:8085/auth/confrim?email="+email+"&token="+token;
        message.setText(res);
        mailSender.send(message);
    }

    @Override
    public void sendPasswordResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sharifov.murad29@gmail.com");
        message.setTo(email);
        message.setSubject("Password Reset Request");
        String resetUrl = "http://localhost:8085/reset-password?token=" + token;
        message.setText("To reset your password, click the link below:\n" + resetUrl);
        mailSender.send(message);
    }
}
