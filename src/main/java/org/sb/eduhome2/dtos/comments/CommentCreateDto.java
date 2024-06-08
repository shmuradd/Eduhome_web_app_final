package org.sb.eduhome2.dtos.comments;

import lombok.Data;

@Data
public class CommentCreateDto {
    private String fullName;
    private String comment;
    private String position;
    private String clas;
    private String image;
}
