package org.sb.eduhome2.repositories;

import org.sb.eduhome2.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
// findByFieldName
// List<UserEntity> findByFirstName
// findFirstByFieldName
// UserEntity findFirstByFirstName