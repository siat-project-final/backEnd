package com.takoyakki.backend.domain.toDo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takoyakki.backend.domain.toDo.model.TodoDate;
import com.takoyakki.backend.domain.toDo.model.TodoItem;
import com.takoyakki.backend.domain.toDo.model.TodoItemRequest;
import com.takoyakki.backend.domain.toDo.model.TodoItemResponse;
import com.takoyakki.backend.domain.toDo.repository.TodoDateMapper;
import com.takoyakki.backend.domain.toDo.repository.TodoItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoDateMapper todoDateMapper;
    private final TodoItemMapper todoItemMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TodoItemResponse> getTodoItemsByDate(LocalDate dateValue) {
        Optional<TodoDate> todoDateOptional = todoDateMapper.findByDateValue(dateValue);
        return todoDateOptional.map(todoDate ->
                        todoItemMapper.findByTodoDateId(todoDate.getId()).stream()
                                .map(item -> new TodoItemResponse(
                                        item.getId(),
                                        item.getTodoDateId(),
                                        item.getTitle(),
                                        item.isCompleted(),
                                        item.getCreatedAt(),
                                        item.getUpdatedAt()))
                                .collect(Collectors.toList()))
                .orElse(List.of());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TodoItemResponse> getTodoItemById(Long id) {
        return todoItemMapper.findById(id)
                .map(item -> new TodoItemResponse(
                        item.getId(),
                        item.getTodoDateId(),
                        item.getTitle(),
                        item.isCompleted(),
                        item.getCreatedAt(),
                        item.getUpdatedAt()));
    }

    @Override
    @Transactional
    public TodoItemResponse createTodoItem(LocalDate dateValue, TodoItemRequest request) {
        TodoDate todoDate = todoDateMapper.findByDateValue(dateValue)
                .orElseGet(() -> {
                    TodoDate newTodoDate = new TodoDate();
                    newTodoDate.setDateValue(dateValue);
                    todoDateMapper.insert(newTodoDate);
                    return newTodoDate;
                });

        TodoItem todoItem = new TodoItem();
        todoItem.setTodoDateId(todoDate.getId());
        todoItem.setTitle(request.getTitle());
        todoItem.setCompleted(request.isCompleted());

        todoItemMapper.insert(todoItem);

        return new TodoItemResponse(
                todoItem.getId(),
                todoItem.getTodoDateId(),
                todoItem.getTitle(),
                todoItem.isCompleted(),
                todoItem.getCreatedAt(),
                todoItem.getUpdatedAt());
    }

    @Override
    @Transactional
    public Optional<TodoItemResponse> updateTodoItem(Long id, TodoItemRequest request) {
        Optional<TodoItem> existingItemOptional = todoItemMapper.findById(id);

        if (existingItemOptional.isPresent()) {
            TodoItem existingItem = existingItemOptional.get();
            existingItem.setTitle(request.getTitle());
            existingItem.setCompleted(request.isCompleted());

            todoItemMapper.update(existingItem);

            return Optional.of(new TodoItemResponse(
                    existingItem.getId(),
                    existingItem.getTodoDateId(),
                    existingItem.getTitle(),
                    existingItem.isCompleted(),
                    existingItem.getCreatedAt(),
                    existingItem.getUpdatedAt()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteTodoItem(Long id) {
        Optional<TodoItem> existingItem = todoItemMapper.findById(id);
        if (existingItem.isPresent()) {
            todoItemMapper.delete(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItemResponse> getAllTodoItems() {
        return todoItemMapper.findAll().stream()
                .map(item -> new TodoItemResponse(
                        item.getId(),
                        item.getTodoDateId(),
                        item.getTitle(),
                        item.isCompleted(),
                        item.getCreatedAt(),
                        item.getUpdatedAt()))
                .collect(Collectors.toList());
    }
}
