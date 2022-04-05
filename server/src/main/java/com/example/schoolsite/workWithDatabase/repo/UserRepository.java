package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPasswordAndRole(String login, String password, String role);
    boolean existsByLogin(String login);
    boolean existsUserByLogin(String login);
}
