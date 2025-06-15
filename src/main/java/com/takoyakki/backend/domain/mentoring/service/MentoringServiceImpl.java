package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.MentorDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringReservationDto;
import com.takoyakki.backend.domain.mentoring.dto.PreConversationDto;
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
    public List<MentoringReservationDto> getReservationsByDate(String date) {
        return mentoringReservationMapper.findReservationsByDate(date);
    }

    @Override
    public void createReservation(MentoringReservationDto reservation) {
        mentoringReservationMapper.insertReservation(reservation);
    }

    @Override
    public List<MentorDto> getMentorList(int offset, int limit) {
        return mentorMapper.findAllMentors(offset, limit);
    }

    @Override
    public MentorDto getMentorDetail(Long mentorId) {
        return mentorMapper.findById(mentorId);
    }

    @Override
    public List<MentoringReservationDto> getMyReservations(Long menteeId) {
        return mentoringReservationMapper.findReservationsByMenteeId(menteeId);
    }

    @Override
    public void cancelReservation(Long reservationId, String cancelReason) {
        mentoringReservationMapper.cancelReservation(reservationId, cancelReason);
    }

    @Override
    public List<MentoringReservationDto> getHistoryReservations(Long menteeId) {
        return mentoringReservationMapper.findHistoryReservationsByMenteeId(menteeId);
    }

    @Override
    public PreConversationDto getPreConversationData(Long mentorId) {
        MentorDto mentorDto = mentorMapper.findById(mentorId);
        List<String> topics = mentorMapper.findConversationTopicsByMentorId(mentorId);
        return new PreConversationDto(mentorDto, topics);
    }

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

    @Override
    public List<MentoringRequestDto> getAllMentoring(int offset, int limit) {
        return mentoringMapper.findAllMentoring(offset, limit);
    }

    @Override
    public MentoringRequestDto getMentoringById(Long id) {
        return mentoringMapper.selectMentoringById(id);
    }

    @Override
    public List<MentoringRequestDto> getMentoringByMentorId(Long mentorId) {
        return mentoringMapper.findMentoringByMentorId(mentorId);
    }

    @Override
    public void createMentoring(MentoringRequestDto mentoringRequestDto) {
        mentoringMapper.insertMentoring(mentoringRequestDto);
    }

    @Override
    public void updateMentoring(MentoringRequestDto mentoringRequestDto) {
        mentoringMapper.updateMentoring(mentoringRequestDto);
    }

    @Override
    public List<MentoringRequestDto> findAllMentoring(int offset, int limit) {
        return List.of();
    }
}
