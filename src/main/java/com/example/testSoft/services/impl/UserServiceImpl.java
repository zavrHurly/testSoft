package com.example.testSoft.services.impl;

import com.example.testSoft.domain.Role;
import com.example.testSoft.domain.User;
import com.example.testSoft.dto.UserDto;
import com.example.testSoft.error.NotFoundException;
import com.example.testSoft.repository.RoleRepository;
import com.example.testSoft.repository.UserRepository;
import com.example.testSoft.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> listFromDb = (List<User>) userRepository.findAll();
        log.info("IN getAll - {} users found", listFromDb.size());
        return listFromDb.stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByName(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    public User update(User user, long id) {
        final User userFromDb = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        userFromDb.updateUser(user);
        return userRepository.save(userFromDb);
    }

    @Override
    public User create(User user) {
        List<Role> userRoles = new ArrayList<>();
        userRoles.add((Role) user.getRoles());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }
}
