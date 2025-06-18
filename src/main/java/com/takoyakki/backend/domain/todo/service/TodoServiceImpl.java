package com.takoyakki.backend.domain.todo.service;

import com.takoyakki.backend.domain.todo.dto.TodoCreateRequestDto;
import com.takoyakki.backend.domain.todo.dto.TodoSelectResponseDto;
import com.takoyakki.backend.domain.todo.dto.TodoUpdateRequestDto;
import com.takoyakki.backend.domain.todo.repository.TodoDateMapper;
import com.takoyakki.backend.domain.todo.repository.TodoItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoDateMapper todoDateMapper;
    private final TodoItemMapper todoItemMapper;

    @Override
    public List<TodoSelectResponseDto> getAllTodos(String memberId, String date) {
        return todoDateMapper.selectTodosByDate(memberId, date);
    }

    @Override
    public TodoSelectResponseDto createTodo(TodoCreateRequestDto request) {
        todoItemMapper.insertTodoItem(request);
        Long createdId = request.getId();
        if (createdId == null) {
            throw new IllegalStateException("할 일 생성 후 ID가 설정되지 않았습니다.");
        }
        Optional<TodoSelectResponseDto> optionalTodo = todoDateMapper.selectTodoById(createdId);
        return optionalTodo.orElseThrow(() -> new IllegalArgumentException("생성된 할 일을 조회할 수 없습니다."));
    }

    @Override
    public boolean updateTodo(Long id, TodoUpdateRequestDto request) {
        return todoItemMapper.updateTodoItem(id, request) > 0;
    }

    @Override
    public boolean deleteTodo(Long id) {
        return todoItemMapper.softDeleteTodoItem(id, LocalDateTime.now()) > 0;
    }
}

