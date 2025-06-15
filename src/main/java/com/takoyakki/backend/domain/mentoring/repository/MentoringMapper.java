package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.dto.MentorDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {

    // 전체 멘토링 조회 (페이징)
    List<MentorDto> findAllMentorings(@Param("offset") int offset, @Param("limit") int limit);

    // ID 기준 멘토링 상세 조회
    MentoringRequestDto selectMentoringById(@Param("id") Long id);

    // 특정 멘토의 멘토링 목록 조회
    List<MentoringRequestDto> findMentoringsByMentorId(@Param("mentorId") Long mentorId);

    // 멘토링 등록
    void insertMentoring(MentoringRequestDto mentoring);

    // 멘토링 정보 수정
    void updateMentoring(MentoringRequestDto mentoring);

    // 멘토링 삭제
    void deleteMentoring(@Param("id") Long id);
}
