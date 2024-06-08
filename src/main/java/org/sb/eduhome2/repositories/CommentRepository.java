package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.StudentComments;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;

public interface CommentRepository extends JpaRepository<StudentComments, Integer> {
}
