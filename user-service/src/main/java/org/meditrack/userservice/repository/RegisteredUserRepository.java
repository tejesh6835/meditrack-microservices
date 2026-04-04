package org.meditrack.userservice.repository;

import org.meditrack.userservice.entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

}
