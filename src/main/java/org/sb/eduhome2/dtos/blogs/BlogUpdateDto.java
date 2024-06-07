package org.sb.eduhome2.dtos.blogs;

import lombok.Data;

@Data
public class BlogUpdateDto {
    private int id;
    private String title;
    private String subtitle;
    private String image;
    private String author;
    private String description;
    private int commentsCount;

}