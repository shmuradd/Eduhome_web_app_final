package org.sb.eduhome2.services.impls;
import org.openqa.selenium.JavascriptExecutor;

import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.replies.ContactCommentDto;
import org.sb.eduhome2.models.ContactMessage;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Speaker;
import org.sb.eduhome2.repositories.ContactMessageRepository;
import org.sb.eduhome2.services.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {
    @Autowired
    private ContactMessageRepository messageRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void saveMessage(ContactCommentDto message) {

        try {
            ContactMessage contactMessage = new ContactMessage();
            contactMessage.setEmail(message.getEmail());
            contactMessage.setReplied(false);
            contactMessage.setMessage(message.getMessage());
            contactMessage.setSubject(message.getSubject());
            contactMessage.setName(message.getName());
            // Add any additional fields here if needed

            messageRepository.save(contactMessage);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ContactMessage> getAllMessages() {
        return messageRepository.findByOrderByRepliedAscIdAsc();
    }
    @Override
    public ContactMessage replyToMessage(Long id, String reply) {
        ContactMessage message = messageRepository.findById(id).orElseThrow();
        message.setReply(reply);
        message.setReplied(true);
        messageRepository.save(message);

        // Prepare the email content
        String emailContent = String.format(
                "Hello %s,\n\nYou got a new message from the EduHome team:\n\n%s\n\nThank you for reaching out to us. We appreciate you taking the time to contact us. Our team has received your message and will get back to you as soon as possible.\n\nBest wishes,\nEduHome team",
                message.getName(),  // Assuming ContactMessage has a getName() method
                reply
        );

        // Send email reply
        sendEmailReply(message.getEmail(), emailContent);

        return message;
    }

    private void sendEmailReply(String recipientEmail, String reply) {
        try {
            // Prepare the email message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(recipientEmail);
            mailMessage.setSubject("Reply from Admin");
            mailMessage.setText(reply);

            // Send the email
            mailSender.send(mailMessage);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error sending email: " + e.getMessage());
            // Optionally, you can rethrow the exception or handle it accordingly
        }
    }



    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sharifov.murad29@gmail.com");  // Set the sender's email address

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }


}
