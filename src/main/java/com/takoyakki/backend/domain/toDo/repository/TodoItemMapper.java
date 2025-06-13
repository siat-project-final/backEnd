package com.takoyakki.backend.domain.toDo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.takoyakki.backend.domain.toDo.model.TodoItem;

@Mapper
public interface TodoItemMapper {
    List<TodoItem> findAll();
    List<TodoItem> findByTodoDateId(Long todoDateId);
    Optional<TodoItem> findById(Long id);
    void insert(TodoItem todoItem);
    void update(TodoItem todoItem);
    void delete(Long id);
}
