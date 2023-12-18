package com.sanjay.blog.services.impl;

import com.sanjay.blog.converter.UserConverter;
import com.sanjay.blog.dto.UserDto;
import com.sanjay.blog.entity.User;
import com.sanjay.blog.exceptions.ResourceNotFoundException;
import com.sanjay.blog.repository.UserRepository;
import com.sanjay.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userConverter.convertDtoToEntity(userDto);
        user = this.userRepository.save(user);
        userDto = this.userConverter.convertEntityToDto(user);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = this.userRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("User", "id" , id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepository.save(user);
        return this.userConverter.convertEntityToDto(updateUser);

    }

    @Override
    public UserDto getUserByID(Long id) {
        User user = this.userRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        return userConverter.convertEntityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(User user : userList) {
            UserDto userDto = this.userConverter.convertEntityToDto(user);
            userDtoList.add(userDto);
        }

        return userDtoList;

    }

    @Override
    public void deleteUser(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userRepository.delete(user);
    }
}
