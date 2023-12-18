package com.sanjay.blog.converter;

import com.sanjay.blog.dto.UserDto;
import com.sanjay.blog.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public User convertDtoToEntity (UserDto userDto) {

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto convertEntityToDto (User user) {

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

}
