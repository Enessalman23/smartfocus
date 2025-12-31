package com.enessalman.dto;

import com.enessalman.entities.Priority;
import com.enessalman.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGoal {
    @NotBlank
    @NotNull
    private String goal;
    @NotNull
    private Status status;
    @NotNull
    private Priority priority;
    @NotNull
    private LocalDate endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
