package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPasswordAndRole(String login, String password, String role);
    boolean existsUserByLogin(String login);
    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);
}
