package com.example.testSoft.services;

import com.example.testSoft.domain.User;
import com.example.testSoft.dto.UserDto;

import java.util.List;

public interface UserService {

    User register(User user);

    List<UserDto> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    User update(User user, long id);

    User create(User user);
}
