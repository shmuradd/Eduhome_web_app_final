package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.StudentComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCommentsRepository extends JpaRepository<StudentComments, Integer> {
}
