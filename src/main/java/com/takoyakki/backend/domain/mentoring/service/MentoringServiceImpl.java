package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringReservationDto;
import com.takoyakki.backend.domain.mentoring.dto.PreConversationDto;
import com.takoyakki.backend.domain.mentoring.model.Mentor;
import com.takoyakki.backend.domain.mentoring.repository.MentorMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class MentoringServiceImpl implements MentoringService {

    private final MentorMapper mentorMapper;
    private final MentoringReservationMapper mentoringReservationMapper;

    // 특정 날짜 기준 예약 조회
    @Override
    public List<MentoringReservationDto> getReservationsByDate(String date) {
        return mentoringReservationMapper.findReservationsByDate(date);
    }

    // 멘토링 예약 신청
    @Override
    public void createReservation(MentoringReservationDto reservation) {
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
    public List<MentoringReservationDto> getMyReservations(Long menteeId) {
        return mentoringReservationMapper.findReservationsByMenteeId(menteeId);
    }

    // 예약 취소 (사유 포함)
    @Override
    public void cancelReservation(Long reservationId, String cancelReason) {
        mentoringReservationMapper.cancelReservation(reservationId, cancelReason);
    }

    // 지난 멘토링 히스토리 조회
    @Override
    public List<MentoringReservationDto> getHistoryReservations(Long menteeId) {
        return mentoringReservationMapper.findHistoryReservationsByMenteeId(menteeId);
    }

    // 사전 대화 초기 데이터 (멘토 + 대화 주제)
    @Override
    public PreConversationDto getPreConversationData(Long mentorId) {
        Mentor mentorDto = mentorMapper.findById(mentorId);
        List<String> topics = mentorMapper.findConversationTopicsByMentorId(mentorId);
        return new PreConversationDto(mentorDto, topics);
    }

    // 사전 대화 작성 및 멘토링 신청
    @Override
    public void applyMentoring(MentoringRequestDto requestDto) {
        MentoringReservationDto reservation = new MentoringReservationDto();
        reservation.setMentorId(requestDto.getMentorId());
        reservation.setMenteeId(requestDto.getMenteeId());
        reservation.setPreConversation(requestDto.getPreConversation());
        reservation.setTitle(requestDto.getTitle());
        reservation.setContent(requestDto.getContent());
        reservation.setDate(requestDto.getMentoringDate().toString());
        reservation.setTime(requestDto.getMentoringTime().toString());
        reservation.setStatus("WAITING");

        mentoringReservationMapper.insertReservation(reservation);
    }
}
