package com.example.testSoft.services;

import com.example.testSoft.AuthUser;
import com.example.testSoft.domain.User;
import com.example.testSoft.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String username) {
        User result = userRepository.findByName(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteUser(long id){ userRepository.deleteById(id);}

    public void update(User user, long id){
        User userFromDB = userRepository.getById(id);
        userFromDB.updateUser(user);
        userRepository.save(userFromDB);
    }
}
