package com.takoyakki.backend.domain.todo.controller;

import com.takoyakki.backend.domain.todo.dto.TodoCreateRequestDto;
import com.takoyakki.backend.domain.todo.dto.TodoSelectResponseDto;
import com.takoyakki.backend.domain.todo.dto.TodoUpdateRequestDto;
import com.takoyakki.backend.domain.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo API", description = "투두리스트 관리 API")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoSelectResponseDto>> getAllTodos(
            @Parameter(description = "사용자 ID", required = true) @RequestParam String memberId,
            @Parameter(description = "날짜 (YYYY-MM-DD)", required = false) @RequestParam(required = false) String date) {

        if (memberId == null || memberId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<TodoSelectResponseDto> todos = todoService.getAllTodos(memberId, date);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<TodoSelectResponseDto> createTodo(@Valid @RequestBody TodoCreateRequestDto request) {
        TodoSelectResponseDto newTodo = todoService.createTodo(request);
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> updateTodo(
            @Parameter(description = "투두 ID") @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequestDto request) {
        boolean success = todoService.updateTodo(id, request);
        return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(Map.of("success", success));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTodo(
            @Parameter(description = "투두 ID") @PathVariable Long id) {
        boolean success = todoService.deleteTodo(id);
        return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(Map.of("success", success));
    }
}