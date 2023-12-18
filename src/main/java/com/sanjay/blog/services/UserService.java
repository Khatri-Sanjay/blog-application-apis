package com.sanjay.blog.services;

import com.sanjay.blog.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);

    UserDto getUserByID(Long id);

    List<UserDto> getAllUsers();

    void deleteUser(Long id);
}
