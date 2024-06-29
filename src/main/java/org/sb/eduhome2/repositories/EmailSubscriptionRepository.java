package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.EmailSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailSubscriptionRepository extends JpaRepository<EmailSubscription, Long> {
    Optional<EmailSubscription> findByEmail(String email);

}

