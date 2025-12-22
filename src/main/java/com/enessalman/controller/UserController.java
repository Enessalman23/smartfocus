package com.enessalman.controller;


import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/api/user")
public class UserController {


    @Autowired
    UserService userService;

    @PutMapping(path = "/update/{id}")
    public DtoUser getUserById(@PathVariable int id,DtoUserRequest dtoUserRequest) {
        return userService.updateUserById(id,dtoUserRequest);
    }

    @DeleteMapping(path = "delete/{id}")
    public DtoUser deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }
    @PostMapping(path = "/add")
    public DtoUser addUser(DtoUserRequest dtoUserRequest){
        return userService.addUser(dtoUserRequest);

    }

}
