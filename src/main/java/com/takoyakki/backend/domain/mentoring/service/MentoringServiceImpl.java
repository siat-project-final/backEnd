package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.PreConversationDto;
import com.takoyakki.backend.domain.mentoring.model.Mentor;
import com.takoyakki.backend.domain.mentoring.model.MentoringReservation;
import com.takoyakki.backend.domain.mentoring.repository.MentorMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    private final MentorMapper mentorMapper;
    private final MentoringReservationMapper mentoringReservationMapper;

    // 특정 날짜 기준 예약 조회
    @Override
    public List<MentoringReservation> getReservationsByDate(String date) {
        return mentoringReservationMapper.findReservationsByDate(date);
    }

    // 멘토링 예약 신청
    @Override
    public void createReservation(MentoringReservation reservation) {
        mentoringReservationMapper.insertReservation(reservation);
    }

    // 멘토 리스트 조회
    @Override
    public List<Mentor> getMentorList(int offset, int limit) {
        return mentorMapper.findAllMentors(offset, limit);
    }

    // 멘토 상세 조회
    @Override
    public Mentor getMentorDetail(Long mentorId) {
        return mentorMapper.findById(mentorId);
    }

    // 멘티 기준 예약 목록 조회
    @Override
    public List<MentoringReservation> getMyReservations(Long menteeId) {
        return mentoringReservationMapper.findReservationsByMenteeId(menteeId);
    }

    // 예약 취소 (사유 포함)
    @Override
    public void cancelReservation(Long reservationId, String cancelReason) {
        mentoringReservationMapper.cancelReservation(reservationId, cancelReason);
    }

    // 지난 멘토링 히스토리 조회
    @Override
    public List<MentoringReservation> getHistoryReservations(Long menteeId) {
        return mentoringReservationMapper.findHistoryReservationsByMenteeId(menteeId);
    }

    // 사전대화

    // 사전 대화 작성 페이지 초기 데이터 조회 (멘토 상세 + 선택 가능한 대화 주제 리스트)
    @Override
    public PreConversationDto getPreConversationData(Long mentorId) {
        Mentor mentor = mentorMapper.findById(mentorId);
        List<String> topics = mentorMapper.findConversationTopicsByMentorId(mentorId); // 대화 주제 리스트 조회용
        return new PreConversationDto(mentor, topics);
    }

    // 사전 대화 작성 및 멘토링 신청 처리
    @Override
    public void applyMentoring(MentoringRequestDto requestDto) {
        MentoringReservation reservation = new MentoringReservation();
        reservation.setMentorId(requestDto.getMentorId());
        reservation.setMenteeId(requestDto.getMenteeId());
        reservation.setPreConversation(requestDto.getPreConversation());
        reservation.setTitle(requestDto.getTitle());
        reservation.setContent(requestDto.getContent());
        reservation.setDate(requestDto.getMentoringDate().toString()); // YYYY-MM-DD
        reservation.setTime(requestDto.getMentoringTime().toString()); // HH:mm
        reservation.setStatus("WAITING");

        mentoringReservationMapper.insertReservation(reservation);
    }


}
