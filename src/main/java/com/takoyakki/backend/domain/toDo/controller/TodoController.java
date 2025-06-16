package com.takoyakki.backend.domain.toDo.controller;

import com.takoyakki.backend.domain.toDo.dto.TodoCreateRequestDto;
import com.takoyakki.backend.domain.toDo.dto.TodoDto;
import com.takoyakki.backend.domain.toDo.dto.TodoUpdateRequestDto;
import com.takoyakki.backend.domain.toDo.dto.TodoSelectResponseDto;
import com.takoyakki.backend.domain.toDo.service.TodoService;

import io.swagger.v3.oas.annotations.tags.Tag; // ★ @Tag는 유지

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "할 일 관리 API", description = "할 일 항목과 관련된 CRUD 작업을 제공합니다.") // ★ @Tag 어노테이션은 유지합니다.
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // --- GET /api/todos : 모든 할 일 조회 ---
    @GetMapping
    public ResponseEntity<List<TodoSelectResponseDto>> getAllTodos() {
        List<TodoDto> todos = todoService.findAllTodos();
        List<TodoSelectResponseDto> responseDtos = todos.stream()
                                                  .map(TodoSelectResponseDto::new)
                                                  .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    // --- GET /api/todos/{todoId} : 특정 ID의 할 일 조회 ---
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoSelectResponseDto> getTodoById(
            @PathVariable Long todoId) {
        TodoDto todo = todoService.findTodoById(todoId);
        if (todo != null) {
            return ResponseEntity.ok(new TodoSelectResponseDto(todo));
        }
        return ResponseEntity.notFound().build();
    }

    // --- POST /api/todos : 새로운 할 일 생성 (TodoCreateRequestDto 사용) ---
    @PostMapping
    public ResponseEntity<TodoSelectResponseDto> createTodo(
            @RequestBody TodoCreateRequestDto requestDto) {
        // 컨트롤러에서는 간단한 DTO 유효성 검사만
        if (requestDto.getMemberId() == null ||
            requestDto.getContents() == null || requestDto.getContents().trim().isEmpty() ||
            requestDto.getDate() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        TodoDto createdTodo = todoService.createTodo(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TodoSelectResponseDto(createdTodo));
    }

    // --- PUT /api/todos/{todoId} : 할 일 업데이트 (TodoUpdateRequestDto 사용) ---
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoSelectResponseDto> updateTodo(
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto requestDto) {
        TodoDto updatedTodo = todoService.updateTodo(todoId, requestDto);
        if (updatedTodo != null) {
            return ResponseEntity.ok(new TodoSelectResponseDto(updatedTodo));
        }
        return ResponseEntity.notFound().build();
    }

    // --- DELETE /api/todos/{todoId} : 할 일 삭제 (논리적 삭제) ---
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long todoId) {
        TodoDto existingTodo = todoService.findTodoById(todoId);
        if (existingTodo != null) {
            todoService.softDeleteTodo(todoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --- GET /api/todos/member/{memberId} : 특정 멤버의 모든 할 일 조회 ---
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<TodoSelectResponseDto>> getTodosByMemberId(
            @PathVariable Long memberId) {
        List<TodoDto> todos = todoService.findTodosByMemberId(memberId);
        if (!todos.isEmpty()) {
            List<TodoSelectResponseDto> responseDtos = todos.stream()
                                                      .map(TodoSelectResponseDto::new)
                                                      .collect(Collectors.toList());
            return ResponseEntity.ok(responseDtos);
        }
        return ResponseEntity.notFound().build();
    }

    // --- GET /api/todos/member/{memberId}/date/{date} : 특정 멤버의 특정 날짜 할 일 조회 ---
    @GetMapping("/member/{memberId}/date/{date}")
    public ResponseEntity<List<TodoSelectResponseDto>> getTodosByMemberIdAndDate(
            @PathVariable Long memberId,
            @PathVariable @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TodoDto> todos = todoService.findTodosByMemberIdAndDate(memberId, date);
        if (!todos.isEmpty()) {
            List<TodoSelectResponseDto> responseDtos = todos.stream()
                                                      .map(TodoSelectResponseDto::new)
                                                      .collect(Collectors.toList());
            return ResponseEntity.ok(responseDtos);
        }
        return ResponseEntity.notFound().build();
    }

    // --- PATCH /api/todos/{todoId}/toggle-checked : 할 일 완료 상태 토글 ---
    @PatchMapping("/{todoId}/toggle-checked")
    public ResponseEntity<TodoSelectResponseDto> toggleCheckedStatus(
            @PathVariable Long todoId,
            @RequestParam boolean isChecked) {
        TodoDto existingTodo = todoService.findTodoById(todoId);
        if (existingTodo != null) {
            todoService.toggleTodoCheckedStatus(todoId, isChecked);
            TodoDto updatedTodo = todoService.findTodoById(todoId);
            return ResponseEntity.ok(new TodoSelectResponseDto(updatedTodo));
        }
        return ResponseEntity.notFound().build();
    }
}