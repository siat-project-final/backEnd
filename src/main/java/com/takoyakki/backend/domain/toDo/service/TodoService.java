package com.takoyakki.backend.domain.toDo.service;

import com.takoyakki.backend.domain.toDo.model.Todo; // ★ Todo 모델 임포트
import java.time.LocalDateTime; // date 컬럼이 TIMESTAMP이므로 LocalDateTime 사용
import java.util.List;

public interface TodoService {
    List<Todo> findAllTodos(); // ★ 메서드 이름 변경 (명확성을 위해)
    Todo findTodoById(Long todoId); // ★ todoId로 조회
    void createTodo(Todo todo); // ★ todo 객체로 생성
    void updateTodo(Todo todo); // ★ todo 객체로 업데이트
    void softDeleteTodo(Long todoId); // ★ 논리적 삭제

    List<Todo> findTodosByMemberId(Long memberId); // ★ 특정 멤버의 할 일 조회
    List<Todo> findTodosByMemberIdAndDate(Long memberId, LocalDateTime date); // ★ 특정 멤버의 특정 날짜 할 일 조회
    void toggleTodoCheckedStatus(Long todoId, boolean isChecked); // ★ 완료 상태 토글
}