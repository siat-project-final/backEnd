package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationAcceptDto;
import com.takoyakki.backend.domain.mentoring.dto.mentoring.MentoringCompleteRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MentoringReservationResponseDto;
import com.takoyakki.backend.domain.mentoring.dto.reservation.MenteeReservationRequestDto;
import com.takoyakki.backend.domain.mentoring.repository.MentorMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {

    private final MentorMapper mentorMapper;
    private final MentoringReservationMapper mentoringReservationMapper;
    private final MentoringMapper mentoringMapper;  // 멘토링 요청 관련 매퍼

    @Override
    public List<MentoringReservationResponseDto> getReservationsByDate(String date) {
        return mentoringReservationMapper.findReservationsByDate(date);
    }

    @Override
    public void createReservation(MentoringReservationResponseDto reservation) {
        mentoringReservationMapper.insertReservation(reservation);
    }

    @Override
    public List<MentoringReservationAcceptDto> getMentorList(int offset, int limit) {
        return mentorMapper.findAllMentors(offset, limit);
    }

    @Override
    public MentoringReservationAcceptDto getMentorDetail(Long mentorId) {
        return mentorMapper.findById(mentorId);
    }

    @Override
    public List<MentoringReservationResponseDto> getMyReservations(Long menteeId) {
        return mentoringReservationMapper.findReservationsByMenteeId(menteeId);
    }

    @Override
    public void cancelReservation(Long reservationId, String cancelReason) {
        mentoringReservationMapper.cancelReservation(reservationId, cancelReason);
    }

    @Override
    public List<MentoringReservationResponseDto> getHistoryReservations(Long menteeId) {
        return mentoringReservationMapper.findHistoryReservationsByMenteeId(menteeId);
    }

    @Override
    public MenteeReservationRequestDto getPreConversationData(Long mentorId) {
        MentoringReservationAcceptDto mentoringReservationAcceptDto = mentorMapper.findById(mentorId);
        List<String> topics = mentorMapper.findConversationTopicsByMentorId(mentorId);
        return new MenteeReservationRequestDto(mentoringReservationAcceptDto, topics);
    }

    @Override
    public void applyMentoring(MentoringCompleteRequestDto requestDto) {
        MentoringReservationResponseDto reservation = new MentoringReservationResponseDto();
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

    @Override
    public List<MentoringCompleteRequestDto> getAllMentoring(int offset, int limit) {
        return mentoringMapper.findAllMentoring(offset, limit);
    }

    @Override
    public MentoringCompleteRequestDto getMentoringById(Long id) {
        return mentoringMapper.selectMentoringById(id);
    }

    @Override
    public List<MentoringCompleteRequestDto> getMentoringByMentorId(Long mentorId) {
        return mentoringMapper.findMentoringByMentorId(mentorId);
    }

    @Override
    public void createMentoring(MentoringCompleteRequestDto mentoringCompleteRequestDto) {
        mentoringMapper.insertMentoring(mentoringCompleteRequestDto);
    }

    @Override
    public void updateMentoring(MentoringCompleteRequestDto mentoringCompleteRequestDto) {
        mentoringMapper.updateMentoring(mentoringCompleteRequestDto);
    }

    @Override
    public List<MentoringCompleteRequestDto> findAllMentoring(int offset, int limit) {
        return List.of();
    }
}
