package com.takoyakki.backend.domain.toDo.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TodoDate {

    private Long id;
    private LocalDate dateValue;

}
