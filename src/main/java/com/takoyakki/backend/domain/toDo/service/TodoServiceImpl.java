package com.takoyakki.backend.domain.toDo.service;

import com.takoyakki.backend.domain.toDo.model.Todo;
import com.takoyakki.backend.domain.toDo.repository.TodoMapper; // ★ TodoMapper 임포트
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 트랜잭션 관리를 위해 추가

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional // 모든 메서드에 트랜잭션 적용
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    @Override
    public List<Todo> findAllTodos() {
        return todoMapper.findAll();
    }

    @Override
    public Todo findTodoById(Long todoId) {
        return todoMapper.findByTodoId(todoId);
    }

    @Override
    public void createTodo(Todo todo) {
        // isDeleted, createdAt, isChecked 등 기본값 설정 (컨트롤러/DB에서 처리될 수도 있음)
        if (todo.getCreatedAt() == null) todo.setCreatedAt(LocalDateTime.now());
        if (todo.getIsChecked() == null) todo.setIsChecked(false); // isChecked가 null이면 false
        todo.setDeleted(false); // 항상 false로 시작
        todoMapper.insert(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        // updatedAt 설정
        todo.setUpdatedAt(LocalDateTime.now());
        todoMapper.update(todo);
    }

    @Override
    public void softDeleteTodo(Long todoId) {
        // 물리적 삭제 대신 논리적 삭제 호출
        todoMapper.softDelete(todoId);
    }

    @Override
    public List<Todo> findTodosByMemberId(Long memberId) {
        return todoMapper.findByMemberId(memberId);
    }

    @Override
    public List<Todo> findTodosByMemberIdAndDate(Long memberId, LocalDateTime date) {
        return todoMapper.findByMemberIdAndDate(memberId, date);
    }

    @Override
    public void toggleTodoCheckedStatus(Long todoId, boolean isChecked) {
        todoMapper.toggleChecked(todoId, isChecked);
    }
}