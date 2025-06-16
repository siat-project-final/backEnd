package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationAcceptDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MentoringMapper {

    // --- 멘토링 관련 ---

    // 전체 멘토링 조회 (페이징)
    List<MentoringCompleteRequestDto> findAllMentoring(@Param("offset") int offset, @Param("limit") int limit);

    // ID 기준 멘토링 상세 조회
    MentoringCompleteRequestDto selectMentoringById(@Param("id") Long id);

    // 특정 멘토의 멘토링 목록 조회
    List<MentoringCompleteRequestDto> findMentoringByMentorId(@Param("mentorId") Long mentorId);

    // 멘토링 등록
    void insertMentoring(MentoringCompleteRequestDto mentoring);

    // 멘토링 정보 수정
    void updateMentoring(MentoringCompleteRequestDto mentoring);

    // --- 멘토 관련 ---

    // 멘토 상세 조회
    MentoringReservationAcceptDto findById(@Param("mentorId") Long mentorId);

    // 멘토 리스트(페이징)
    List<MentoringReservationAcceptDto> findAllMentors(@Param("offset") int offset, @Param("limit") int limit);

    // 멘토별 사전 대화 주제 조회
    List<String> findConversationTopicsByMentorId(@Param("mentorId") Long mentorId);

}