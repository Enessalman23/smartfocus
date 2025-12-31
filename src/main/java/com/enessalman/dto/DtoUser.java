package com.enessalman.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @NotNull
    @Size(min = 1 ,max = 25,message = "Username must be 1 or 25 character")
    private String username;
    @NotBlank
    @NotNull
    @Size(min = 4 ,max = 15,message = "password must be 4 or 15 character")
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
