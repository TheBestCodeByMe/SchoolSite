package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsRepository extends JpaRepository<Parents, Long> {
}
