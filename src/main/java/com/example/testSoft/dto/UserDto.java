package com.example.testSoft.dto;

import com.example.testSoft.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private LocalDate dateOfBirth;
    private String email;
    private long callNumber;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setFatherName(fatherName);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        user.setCallNumber(callNumber);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setFatherName(user.getFatherName());
        userDto.setEmail(user.getEmail());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setCallNumber(user.getCallNumber());

        return userDto;
    }
}
