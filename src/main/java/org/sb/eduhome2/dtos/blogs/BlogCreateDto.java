package org.sb.eduhome2.dtos.blogs;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class BlogCreateDto {
    private String title;
    private String subtitle;
    private String image;
    private MultipartFile imageFile;

    private String author;
    private LocalDateTime createTime;
    private String description;
    private int commentsCount;

}
