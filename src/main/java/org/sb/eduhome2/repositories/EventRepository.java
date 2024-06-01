package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
