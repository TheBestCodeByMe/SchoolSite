package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Role;
import com.example.schoolsite.enumiration.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
