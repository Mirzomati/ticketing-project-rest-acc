package com.cydeo.controller;

import com.cydeo.annotation.ExecutionTime;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Controller", description = "User APIs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExecutionTime
    @GetMapping
    @Operation(summary = "Get all users")
    @Tag(name = "User Get Methods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users are successfully retrieved",
            content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Users could not be retrieved",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ResponseWrapper> getAllUsers(){

        List<UserDTO> userDTOList = userService.listAllUsers();

        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved", userDTOList));

    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user by username")
    @Tag(name = "User Get Methods")
    public ResponseEntity<ResponseWrapper> getUserByUsername(@PathVariable String username){

        UserDTO userDTO = userService.findByUserName(username);

        return ResponseEntity.ok(new ResponseWrapper("User Successfully retrieved", userDTO));

    }

    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody @Valid UserDTO user){

        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Successfully created user", 201));
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update user")
    public ResponseEntity<ResponseWrapper> updateUser(@PathVariable String username, @RequestBody UserDTO user){

        userService.update(username, user);

        return ResponseEntity.ok((new ResponseWrapper("Successfully updated user")));

    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable String username){

        userService.delete(username);
        return ResponseEntity.ok(new ResponseWrapper("Successfully deleted user"));
    }
}
