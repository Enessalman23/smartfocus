package com.enessalman.service;

import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.entities.User;
import com.enessalman.mapper.IUserMapper;
import com.enessalman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private final UserRepository userRepository;
    private final IUserMapper userMapper;

    public DtoUser updateUserById(int id, DtoUserRequest dtoUserRequest) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User dbuser = optional.get();
            dbuser.setUsername(dtoUserRequest.getUsername());
            dbuser.setPassword(dtoUserRequest.getPassword());
           User updatedUser = userRepository.save(dbuser);
            return userMapper.toDto(updatedUser);
        }
       return null;
    }

    public DtoUser addUser(DtoUserRequest dtoUserRequest) {
        User user = userMapper.toEntity(dtoUserRequest);
        User dbUser = userRepository.save(user);
        return userMapper.toDto(dbUser);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
