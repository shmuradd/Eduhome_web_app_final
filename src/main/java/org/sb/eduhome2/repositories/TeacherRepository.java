package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
