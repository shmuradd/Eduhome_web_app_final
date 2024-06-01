package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
