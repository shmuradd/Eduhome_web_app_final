package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByOrderByRepliedAscIdAsc();

}
