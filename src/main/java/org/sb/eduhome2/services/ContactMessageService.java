package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.replies.ContactCommentDto;
import org.sb.eduhome2.models.ContactMessage;

import java.util.List;

public interface ContactMessageService {
    public void saveMessage(ContactCommentDto message);
    public List<ContactMessage> getAllMessages();
    public ContactMessage replyToMessage(Long id, String reply);

}
