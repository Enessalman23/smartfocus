package com.enessalman.controller;


import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/api/user")
public class UserController {


    private final UserService userService;

    //constructor dependency injection
    @Autowired //eğer tek parametreliyse autowireda gerek yok
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<DtoUser> updateUserById(@PathVariable int id, @RequestBody DtoUserRequest dtoUserRequest) {
        DtoUser updatedUser = userService.updateUserById(id, dtoUserRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<DtoUser> addUser(@RequestBody DtoUserRequest dtoUserRequest) {
        DtoUser savedUser = userService.addUser(dtoUserRequest);
        return ResponseEntity.ok().body(savedUser);
    }

}
