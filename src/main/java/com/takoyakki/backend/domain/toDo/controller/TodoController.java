package com.takoyakki.backend.domain.toDo.controller;

import com.takoyakki.backend.domain.toDo.model.TodoItemRequest;
import com.takoyakki.backend.domain.toDo.model.TodoItemResponse;
import com.takoyakki.backend.domain.toDo.service.TodoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {


    private final TodoService todoService;

    // 1. 특정 날짜의 TODO 아이템 조회 (GET /api/todos?date=YYYY-MM-DD)
    @GetMapping
    public ResponseEntity<List<TodoItemResponse>> getTodoItemsByDate(
            @RequestParam(required = false) LocalDate date) {
        List<TodoItemResponse> todoItems;
        if (date != null) {
            todoItems = todoService.getTodoItemsByDate(date);
        } else {
            todoItems = todoService.getAllTodoItems(); // 날짜가 없으면 모든 TODO 반환
        }
        return ResponseEntity.ok(todoItems);
    }

    // 2. 단일 TODO 조회 (GET /api/todos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<TodoItemResponse> getTodoItemById(@PathVariable Long id) {
        return todoService.getTodoItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. 새 TODO 생성 (POST /api/todos?date=YYYY-MM-DD)
    @PostMapping
    public ResponseEntity<TodoItemResponse> createTodoItem(
            @RequestParam("date") @NotNull LocalDate dateValue, // 필수 파라미터로 날짜를 받음
            @Valid @RequestBody TodoItemRequest request) { // 요청 본문으로 TODO 내용 받음
        TodoItemResponse createdTodo = todoService.createTodoItem(dateValue, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    // 4. TODO 업데이트 (PUT /api/todos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<TodoItemResponse> updateTodoItem(
            @PathVariable Long id,
            @Valid @RequestBody TodoItemRequest request) {
        return todoService.updateTodoItem(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. TODO 삭제 (DELETE /api/todos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        if (todoService.deleteTodoItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
