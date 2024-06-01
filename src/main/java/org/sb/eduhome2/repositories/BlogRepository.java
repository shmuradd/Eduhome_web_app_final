package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository  extends JpaRepository<Blog, Long> {
}
