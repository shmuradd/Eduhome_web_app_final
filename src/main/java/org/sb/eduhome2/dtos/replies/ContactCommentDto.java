package org.sb.eduhome2.dtos.replies;

import lombok.Data;

@Data
public class ContactCommentDto {
    private String name;
    private String email;
    private String subject;
    private String message;
}
