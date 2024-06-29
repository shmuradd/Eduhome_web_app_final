package org.sb.eduhome2.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.sb.eduhome2.models.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository  extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContainingIgnoreCaseOrSubtitleContainingIgnoreCase(String title, String subtitle);
    Page<Blog> findByIsDeletedFalse(PageRequest pageRequest);

}
