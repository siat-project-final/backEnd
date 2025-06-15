package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.MentorDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringReservationDto;
import com.takoyakki.backend.domain.mentoring.dto.PreConversationDto;


import java.util.List;

public interface MentoringService {

    // 특정 날짜 기준 예약 조회
    List<MentoringReservationDto> getReservationsByDate(String date);

    // 멘토링 예약 신청
    void createReservation(MentoringReservationDto reservationDto);

    // 멘토 리스트 조회 (페이징 지원)
    List<MentorDto> getMentorList(int offset, int limit);

    // 멘토 상세 조회
    MentorDto getMentorDetail(Long mentorId);

    // 멘티 기준 본인 예약 목록 조회 (대기/확정 등)
    List<MentoringReservationDto> getMyReservations(Long menteeId);

    // 멘토링 예약 취소 (취소 사유 포함)
    void cancelReservation(Long reservationId, String cancelReason);

    // 지난 멘토링 히스토리 조회
    List<MentoringReservationDto> getHistoryReservations(Long menteeId);

    //사전대화

    // 사전 대화 작성 페이지 초기 데이터 조회 (멘토 상세 + 선택 가능한 대화 주제 리스트)
    PreConversationDto getPreConversationData(Long mentorId);

    // 사전 대화 작성 및 멘토링 신청 처리
    void applyMentoring(MentoringRequestDto requestDto);

    //멘토링매퍼
    // 멘토링 정보 (페이징)
    List<MentoringRequestDto> getAllMentoring(int offset, int limit);
    // ID 기준 멘토링 상세 조회
    MentoringRequestDto getMentoringById(Long id);

    // 특정 멘토의 멘토링 목록 조회
    List<MentoringRequestDto> getMentoringByMentorId(Long mentorId);

    // 멘토링 등록, 수정, 삭제도 추가 가능
    void createMentoring(MentoringRequestDto mentoringRequestDto);

    void updateMentoring(MentoringRequestDto mentoringRequestDto);

//전체멘토링 조회
    List<MentoringRequestDto> findAllMentoring(int offset, int limit);
}
