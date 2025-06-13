package com.takoyakki.backend.domain.toDo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.takoyakki.backend.domain.toDo.model.TodoDate;

@Mapper
public interface TodoDateMapper {
    Optional<TodoDate> findByDateValue(LocalDate dateValue);
    void insert(TodoDate todoDate);

}
