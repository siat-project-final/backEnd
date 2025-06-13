package com.takoyakki.backend.domain.toDo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.takoyakki.backend.domain.toDo.model.TodoItemRequest;
import com.takoyakki.backend.domain.toDo.model.TodoItemResponse;

public interface TodoService {
    List<TodoItemResponse> getTodoItemsByDate(LocalDate dateValue);
    Optional<TodoItemResponse> getTodoItemById(Long id);
    TodoItemResponse createTodoItem(LocalDate dateValue, TodoItemRequest request);
    Optional<TodoItemResponse> updateTodoItem(Long id, TodoItemRequest request);
    boolean deleteTodoItem(Long id);
    List<TodoItemResponse> getAllTodoItems();
}
