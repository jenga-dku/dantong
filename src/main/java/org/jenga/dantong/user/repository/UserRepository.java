package org.jenga.dantong.user.repository;

import java.util.Optional;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByStudentId(String studentId);

}
