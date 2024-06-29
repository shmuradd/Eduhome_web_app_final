package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    Page<Event> findByIsDeletedFalse(PageRequest pageRequest);

}
