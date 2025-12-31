package com.enessalman.service;

import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.entities.User;
import com.enessalman.mapper.IUserMapper;
import com.enessalman.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private final UserRepository userRepository;
    private final IUserMapper userMapper;

    public DtoUser updateUserById(int id, DtoUserRequest dtoUserRequest) {
        log.info("Kullanıcı güncelleme isteği alındı. Kullanıcı Adı: {}",dtoUserRequest.getUsername() );
        try {
            Optional<User> optional = userRepository.findById(id);
            if (optional.isPresent()) {
                User dbuser = optional.get();
                dbuser.setUsername(dtoUserRequest.getUsername());
                dbuser.setPassword(dtoUserRequest.getPassword());
                User updatedUser = userRepository.save(dbuser);
                log.info("Kullanıcı Güncellendi. ID: {}",updatedUser.getId());
                return userMapper.toDto(updatedUser);
            }
        } catch (Exception e) {
           log.error("Kullanıcı güncellenirken bir hata oluştu! Mesaj: {}",e.getMessage());
            throw  e;

        }
        return null;
    }

    public DtoUser addUser(DtoUserRequest dtoUserRequest) {
        log.info("Yeni bir kullanıcı ekleme isteği alındı. Kullanıcı adı: {}", dtoUserRequest.getUsername());
        try {

            User user = userMapper.toEntity(dtoUserRequest);
            User dbUser = userRepository.save(user);
            log.info("Kullanıcı Başarılı şekilde sisteme eklendi. ID: {}",dbUser.getId());
            return userMapper.toDto(dbUser);

        } catch (Exception e) {
            log.error("Kullanıcı kaydedilirken hata oluştu! Mesaj: {}", e.getMessage());
            throw e;
        }

    }

    public void deleteUserById(int id) {
        log.info("Kullanıcı Silme isteği alındı. ID: {}",id);
        try {
            userRepository.deleteById(id);
            log.info("Kullanıcı Başarıyla Silindi. ID: {}",id);
        }catch (Exception e){
            log.error("Kullanıcı Silinemedi. Mesaj: {}",e.getMessage());
            throw e;
        }


    }
}
