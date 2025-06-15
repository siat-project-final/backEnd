package com.takoyakki.backend.domain.toDo.controller;

import com.takoyakki.backend.domain.toDo.model.Todo; // ★ Todo 모델 임포트
import com.takoyakki.backend.domain.toDo.service.TodoService;
import com.takoyakki.backend.domain.toDo.dto.TodoItemRequestDto; // ★ TodoRequestDto 임포트

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime; // date 컬럼이 TIMESTAMP이므로 LocalDateTime 사용
import java.util.List;

@Tag(name = "할 일 관리 API", description = "할 일 항목과 관련된 CRUD 작업을 제공합니다.")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // --- GET /api/todos : 모든 할 일 조회 (수정 필요 없거나 필터링 추가) ---
    // 일반적으로는 특정 멤버의 할 일만 조회하므로, 이 API는 삭제하거나 수정해야 할 수 있습니다.
    @Operation(summary = "모든 할 일 항목 조회", description = "논리적으로 삭제되지 않은 모든 할 일 항목을 가져옵니다. (주의: 다중 사용자 환경에서는 memberId로 필터링하는 것이 일반적)",
               responses = {
                       @ApiResponse(responseCode = "200", description = "모든 할 일 항목 조회 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class)))
               })
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.findAllTodos();
        return ResponseEntity.ok(todos);
    }

    // --- GET /api/todos/{todoId} : 특정 ID의 할 일 조회 ---
    @Operation(summary = "특정 ID의 할 일 항목 조회", description = "주어진 todoId에 해당하는 할 일 항목을 가져옵니다. 논리적으로 삭제된 할 일은 제외됩니다.",
               responses = {
                       @ApiResponse(responseCode = "200", description = "할 일 항목 조회 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class))),
                       @ApiResponse(responseCode = "404", description = "할 일 항목을 찾을 수 없음")
               })
    @GetMapping("/{todoId}") // ★ 경로 변수 이름 변경
    public ResponseEntity<Todo> getTodoById(
            @Parameter(description = "조회할 할 일 항목의 ID", required = true, example = "1")
            @PathVariable Long todoId) { // ★ 파라미터 이름 변경
        Todo todo = todoService.findTodoById(todoId);
        if (todo != null) {
            return ResponseEntity.ok(todo);
        }
        return ResponseEntity.notFound().build();
    }

    // --- POST /api/todos : 새로운 할 일 생성 (TodoRequestDto 사용) ---
    @Operation(summary = "새로운 할 일 항목 생성", description = "새로운 할 일 항목을 데이터베이스에 추가합니다.",
               responses = {
                       @ApiResponse(responseCode = "201", description = "할 일 항목 생성 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class))),
                       @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터 (필수 필드 누락 등)")
               })
    @PostMapping
    public ResponseEntity<Todo> createTodo(
            @RequestBody TodoItemRequestDto requestDto) { // ★ TodoRequestDto로 변경
        // 필수 필드 검증
        if (requestDto.getMemberId() == null ||
            requestDto.getContents() == null || requestDto.getContents().trim().isEmpty() ||
            requestDto.getDate() == null) {
            return ResponseEntity.badRequest().body(null); // 에러 응답 개선 가능
        }

        // DTO를 모델 엔티티로 변환
        Todo todo = new Todo();
        todo.setMemberId(requestDto.getMemberId());
        todo.setContents(requestDto.getContents());
        todo.setDate(requestDto.getDate());
        todo.setIsChecked(requestDto.getIsChecked() != null ? requestDto.getIsChecked() : false); // null인 경우 false
        todo.setCreatedAt(LocalDateTime.now()); // 서버에서 설정
        todo.setDeleted(false); // 생성 시에는 항상 false

        todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    // --- PUT /api/todos/{todoId} : 할 일 업데이트 (TodoRequestDto 사용) ---
    @Operation(summary = "할 일 항목 업데이트", description = "주어진 todoId의 할 일 항목을 업데이트합니다.",
               responses = {
                       @ApiResponse(responseCode = "200", description = "할 일 항목 업데이트 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class))),
                       @ApiResponse(responseCode = "404", description = "할 일 항목을 찾을 수 없음"),
                       @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
               })
    @PutMapping("/{todoId}") // ★ 경로 변수 이름 변경
    public ResponseEntity<Todo> updateTodo(
            @Parameter(description = "업데이트할 할 일 항목의 ID", required = true, example = "1")
            @PathVariable Long todoId,
            @RequestBody TodoItemRequestDto requestDto) { // ★ TodoRequestDto로 변경
        Todo existingTodo = todoService.findTodoById(todoId);
        if (existingTodo != null) {
            // 필수 필드 검증 (업데이트 시에도 중요)
            if (requestDto.getContents() == null || requestDto.getContents().trim().isEmpty() ||
                requestDto.getDate() == null) {
                return ResponseEntity.badRequest().build();
            }

            // DTO로부터 모델 엔티티 업데이트
            existingTodo.setContents(requestDto.getContents());
            existingTodo.setDate(requestDto.getDate());
            existingTodo.setIsChecked(requestDto.getIsChecked() != null ? requestDto.getIsChecked() : existingTodo.getIsChecked());
            existingTodo.setUpdatedAt(LocalDateTime.now());

            todoService.updateTodo(existingTodo);
            return ResponseEntity.ok(existingTodo);
        }
        return ResponseEntity.notFound().build();
    }

    // --- DELETE /api/todos/{todoId} : 할 일 삭제 (논리적 삭제) ---
    @Operation(summary = "할 일 항목 논리적 삭제", description = "주어진 todoId에 해당하는 할 일 항목을 논리적으로 삭제합니다. (실제 데이터는 삭제되지 않음)",
               responses = {
                       @ApiResponse(responseCode = "204", description = "할 일 항목 논리적 삭제 성공 (내용 없음)"),
                       @ApiResponse(responseCode = "404", description = "할 일 항목을 찾을 수 없음")
               })
    @DeleteMapping("/{todoId}") // ★ 경로 변수 이름 변경
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "삭제할 할 일 항목의 ID", required = true, example = "1")
            @PathVariable Long todoId) { // ★ 파라미터 이름 변경
        Todo existingTodo = todoService.findTodoById(todoId);
        if (existingTodo != null) {
            todoService.softDeleteTodo(todoId); // ★ 논리적 삭제 메서드 호출
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }

    // --- GET /api/todos/member/{memberId} : 특정 멤버의 모든 할 일 조회 ---
    @Operation(summary = "특정 멤버의 모든 할 일 항목 조회", description = "지정된 memberId에 해당하는 모든 할 일 항목을 가져옵니다. 논리적으로 삭제된 할 일은 제외됩니다.",
               responses = {
                       @ApiResponse(responseCode = "200", description = "할 일 항목 조회 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class))),
                       @ApiResponse(responseCode = "404", description = "해당 멤버의 할 일 항목을 찾을 수 없음")
               })
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Todo>> getTodosByMemberId(
            @Parameter(description = "할 일을 조회할 멤버의 ID", required = true, example = "1")
            @PathVariable Long memberId) {
        List<Todo> todos = todoService.findTodosByMemberId(memberId);
        if (!todos.isEmpty()) {
            return ResponseEntity.ok(todos);
        }
        return ResponseEntity.notFound().build();
    }

    // --- GET /api/todos/member/{memberId}/date/{date} : 특정 멤버의 특정 날짜 할 일 조회 ---
    @Operation(summary = "특정 멤버의 특정 날짜 할 일 항목 조회", description = "지정된 memberId와 날짜에 해당하는 할 일 항목을 가져옵니다. 논리적으로 삭제된 할 일은 제외됩니다.",
               parameters = {
                       @Parameter(name = "memberId", description = "할 일을 조회할 멤버의 ID", required = true, example = "1"),
                       @Parameter(name = "date", description = "조회할 날짜 (YYYY-MM-DDTHH:MM:SS 형식)", required = true, example = "2025-06-15T10:00:00")
               },
               responses = {
                       @ApiResponse(responseCode = "200", description = "할 일 항목 조회 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class))),
                       @ApiResponse(responseCode = "404", description = "해당 날짜에 할 일 항목을 찾을 수 없음")
               })
    @GetMapping("/member/{memberId}/date/{date}")
    public ResponseEntity<List<Todo>> getTodosByMemberIdAndDate(
            @PathVariable Long memberId,
            @PathVariable @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) { // ★ DateTimeFormat 어노테이션 확인
        List<Todo> todos = todoService.findTodosByMemberIdAndDate(memberId, date);
        if (!todos.isEmpty()) {
            return ResponseEntity.ok(todos);
        }
        return ResponseEntity.notFound().build();
    }

    // --- PATCH /api/todos/{todoId}/toggle-checked : 할 일 완료 상태 토글 ---
    @Operation(summary = "할 일 항목 완료 상태 토글", description = "지정된 todoId의 할 일 항목 완료 상태를 변경합니다.",
               responses = {
                       @ApiResponse(responseCode = "200", description = "상태 변경 성공",
                                    content = @Content(schema = @Schema(implementation = Todo.class))),
                       @ApiResponse(responseCode = "404", description = "할 일 항목을 찾을 수 없음")
               })
    @PatchMapping("/{todoId}/toggle-checked")
    public ResponseEntity<Todo> toggleCheckedStatus(
            @Parameter(description = "상태를 변경할 할 일 항목의 ID", required = true, example = "1")
            @PathVariable Long todoId,
            @Parameter(description = "새로운 완료 상태 (true/false)", required = true, example = "true")
            @RequestParam boolean isChecked) {
        Todo existingTodo = todoService.findTodoById(todoId);
        if (existingTodo != null) {
            todoService.toggleTodoCheckedStatus(todoId, isChecked);
            // 업데이트된 Todo 객체를 다시 조회하여 반환하는 것이 일반적입니다.
            Todo updatedTodo = todoService.findTodoById(todoId);
            return ResponseEntity.ok(updatedTodo);
        }
        return ResponseEntity.notFound().build();
    }
}