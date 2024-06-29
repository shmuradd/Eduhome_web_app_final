package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByNameContainingIgnoreCase(String title);
    List<Course> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    Page<Course> findByIsDeletedFalse(PageRequest pageRequest);

}
