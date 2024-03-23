package com.example.testSoft.repository;

import com.example.testSoft.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getById(long id);

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    User getByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name = :name")
    User findByName(String name);
}
