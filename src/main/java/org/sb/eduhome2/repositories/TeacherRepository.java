package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);

}

