package com.enessalman.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {

    private String username;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
