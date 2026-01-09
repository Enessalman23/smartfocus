package com.enessalman.controller;


import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/api/user")
public class UserController {

    private final UserService userService;

    //constructor dependency injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DtoUser> getUserById(@PathVariable int id){
        DtoUser getUser = userService.getUserById(id);
        return ResponseEntity.ok().body(getUser);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DtoUser> updateUserById(@PathVariable int id, @Valid @RequestBody DtoUserRequest dtoUserRequest) {
        DtoUser updatedUser = userService.updateUserById(id, dtoUserRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<DtoUser> addUser(@Valid @RequestBody DtoUserRequest dtoUserRequest) {
        DtoUser savedUser = userService.addUser(dtoUserRequest);
        return ResponseEntity.ok().body(savedUser);
    }

}
