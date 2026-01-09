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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGoalRequest {

    @NotBlank
    @NotNull
    private String goal;
    @NotNull
    private Priority priority;
    @NotNull
    private Status status;
    @NotNull
    private LocalDate endAt;
    private int userId;
}
