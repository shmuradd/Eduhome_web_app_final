package org.sb.eduhome2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    private String subtitle;
    private String image;
    @CreationTimestamp
    private LocalDateTime createTime;
    private String author;
    @Column(nullable = false) // Make isDeleted persistent

    private boolean isDeleted = false;

    @Column(nullable = false)
    @NotBlank(message = "Təsvir boş saxlanıla bilməz!")
    private String description;
    @Column(name = "comments_count", nullable = false, columnDefinition = "integer default 0")
    private int commentsCount;

}
