package org.sb.eduhome2.dtos.blogs;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data
public class BlogDto {
    private int id;
    private String title;
    private String subtitle;
    private String image;
    private LocalDateTime createTime;
    private String author;
    private int commentsCount;

}
