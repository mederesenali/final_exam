package com.example.final_exam.repository;

import com.example.final_exam.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);
}
