package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
