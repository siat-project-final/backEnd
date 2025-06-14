package com.takoyakki.backend.domain.mentoring.service;

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

    // 예약 삭제 (단순 삭제 - 필요 시 삭제)
    @Override
    public void deleteReservation(Long id) {
        mentoringReservationMapper.deleteReservation(id);
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
}
