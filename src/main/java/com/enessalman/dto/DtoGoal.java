package com.enessalman.dto;

import com.enessalman.entities.Priority;
import com.enessalman.entities.Status;
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
    private String goal;
    private Status status;
    private Priority priority;
    private LocalDate endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
