package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.EmailSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSubscriptionRepository extends JpaRepository<EmailSubscription, Long> {
}
