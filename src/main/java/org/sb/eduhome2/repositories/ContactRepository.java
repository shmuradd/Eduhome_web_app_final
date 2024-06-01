package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
