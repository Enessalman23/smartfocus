package com.enessalman.mapper;


import com.enessalman.dto.DtoUser;
import com.enessalman.dto.DtoUserRequest;
import com.enessalman.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// Spring bean olarak yönetilmesi için
public interface IUserMapper {
    // User Entity -> DtoUser (Geri dönüş için)
    DtoUser toDto(User user);

    // DtoUserRequest -> User Entity (Kaydetme için)
    User toEntity(DtoUserRequest dtoUserRequest);
}
