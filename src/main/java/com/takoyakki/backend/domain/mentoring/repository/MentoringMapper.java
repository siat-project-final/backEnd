package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentoring;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {

    // 전체 멘토링 조회 (페이징)
    List<Mentoring> findAllMentorings(@Param("offset") int offset, @Param("limit") int limit);

    // ID 기준 멘토링 상세 조회
    Mentoring selectMentoringById(@Param("id") Long id);

    // 특정 멘토의 멘토링 목록 조회
    List<Mentoring> findMentoringsByMentorId(@Param("mentorId") Long mentorId);

    // 멘토링 등록
    void insertMentoring(Mentoring mentoring);

    // 멘토링 정보 수정
    void updateMentoring(Mentoring mentoring);

    // 멘토링 삭제
    void deleteMentoring(@Param("id") Long id);
}
