package com.enessalman.service;

import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.entities.User;
import com.enessalman.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public DtoUser updateUserById(int id ,DtoUserRequest dtoUserRequest) {
        DtoUser dtoUser = new DtoUser();
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            User dbuser = optional.get();
            dbuser.setUsername(dtoUserRequest.getUsername());
            dbuser.setPassword(dtoUserRequest.getPassword());
            userRepository.save(dbuser);
            BeanUtils.copyProperties(dbuser,dtoUser);
        }
        return  dtoUser;


    }
    public DtoUser addUser(DtoUserRequest dtoUserRequest) {
        DtoUser dtoUser = new DtoUser();
        User user = new User();
        BeanUtils.copyProperties(dtoUserRequest,user);
        User dbUser = userRepository.save(user);
        BeanUtils.copyProperties(dbUser,dtoUser);
        return dtoUser;
    }
}
