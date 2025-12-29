package com.enessalman.dto;

import com.enessalman.entities.Priority;
import com.enessalman.entities.Status;
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

    private String goal;
    private Priority priority;
    private Status status;
    private LocalDate endAt;
    private int userId;
}
