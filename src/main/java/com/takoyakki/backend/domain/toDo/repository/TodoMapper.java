package com.takoyakki.backend.domain.toDo.repository;

import java.util.List;
import java.time.LocalDateTime;

import com.takoyakki.backend.domain.toDo.model.Todo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TodoMapper { // ★ 인터페이스 이름 변경
    List<Todo> findAll();
    Todo findByTodoId(@Param("todoId") Long todoId); // ★ findById -> findByTodoId
    void insert(Todo todo);
    void update(Todo todo);
    void delete(@Param("todoId") Long todoId); // ★ deleteByTodoId

    // 특정 멤버의 모든 할 일 조회
    List<Todo> findByMemberId(@Param("memberId") Long memberId);

    // 특정 멤버의 특정 날짜 할 일 조회
    List<Todo> findByMemberIdAndDate(@Param("memberId") Long memberId, @Param("date") LocalDateTime date);

    // 논리적 삭제를 위한 메서드
    void softDelete(@Param("todoId") Long todoId);
    // 완료 여부 토글
    void toggleChecked(@Param("todoId") Long todoId, @Param("isChecked") boolean isChecked);
}
