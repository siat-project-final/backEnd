package com.takoyakki.backend.domain.todo.service;

import com.takoyakki.backend.domain.todo.dto.TodoCreateRequestDto;
import com.takoyakki.backend.domain.todo.dto.TodoSelectResponseDto;
import com.takoyakki.backend.domain.todo.dto.TodoUpdateRequestDto;
import com.takoyakki.backend.domain.todo.repository.TodosMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodosMapper todosMapper;

    @Override
    public List<TodoSelectResponseDto> getAllTodos(Long memberId, String date) {
        return todosMapper.selectAllTodos(memberId, date);
    }

    @Override
    public Long createTodo(TodoCreateRequestDto request) {
        todosMapper.insertTodo(request);
        return request.getTodoId();
    }

    @Override
    public boolean updateTodo(Long id, TodoUpdateRequestDto request) {
        return todosMapper.updateTodo(id, request) > 0;
    }

    @Override
    public boolean softDeleteTodo(Long id) {
        return todosMapper.softDeleteTodo(id) > 0;
    }

    @Override
    public boolean updateTodoToggle(Long id) {
        return todosMapper.updateTodoToggle(id) > 0;
    }
}
