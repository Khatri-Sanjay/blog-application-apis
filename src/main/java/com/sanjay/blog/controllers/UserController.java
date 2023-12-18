package com.sanjay.blog.controllers;

import com.sanjay.blog.Response.ApiResponse;
import com.sanjay.blog.dto.UserDto;
import com.sanjay.blog.exceptions.ResourceNotFoundException;
import com.sanjay.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> creatUser (@RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUser () {
        List <UserDto> userDtoList = this.userService.getAllUsers();
        ResponseEntity<List<UserDto>> responseEntity = new ResponseEntity<>(userDtoList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getById (@PathVariable Long id) {
        UserDto userDto = this.userService.getUserByID(id);
        ResponseEntity <UserDto> responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        userDto = this.userService.updateUser(userDto, id);
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(userDto, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        this.userService.deleteUser(id);
        return new ResponseEntity(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

}
