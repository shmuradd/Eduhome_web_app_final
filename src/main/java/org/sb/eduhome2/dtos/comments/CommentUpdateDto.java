package org.sb.eduhome2.dtos.comments;

import lombok.Data;

@Data
public class CommentUpdateDto {
    private int id;
    private String fullName;
    private String comment;
    private String position;
    private String clas;
    private String image;
}
