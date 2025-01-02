package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllUsers(){

        List<UserDTO> userDTOList = userService.listAllUsers();

        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved", userDTOList));

    }

    @GetMapping("/{username}")
    public ResponseEntity<ResponseWrapper> getUserByUsername(@PathVariable String username){

        UserDTO userDTO = userService.findByUserName(username);

        return ResponseEntity.ok(new ResponseWrapper("User Successfully retrieved", userDTO));

    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){

        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Successfully created user", 201));
    }

    @PutMapping("/{username}")
    public ResponseEntity<ResponseWrapper> updateUser(@PathVariable String username, @RequestBody UserDTO user){

        userService.update(username, user);

        return ResponseEntity.ok((new ResponseWrapper("Successfully updated user", 200)));

    }

}
