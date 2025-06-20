package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.reservation.*;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringReservationServiceImpl implements MentoringReservationService {

    private final MentoringReservationMapper reservationMapper;

    @Override
    public void createReservation(MemberReservationRequestDto requestDto) {
        reservationMapper.insertReservation(requestDto);
    }

    @Override
    public List<MentoringReservationResponseDto> getReservationsByMemberId(Long memberId) {
        return reservationMapper.selectReservationsByMemberId(memberId);
    }

    @Override
    public List<MentoringReservationResponseDto> getReservationsByMentorId(Long mentorId) {
        return reservationMapper.selectReservationsByMentorId(mentorId);
    }

    @Override
    public void acceptReservation(Long reservationId) {
        reservationMapper.updateReservationToAccepted(reservationId);
    }

    @Override
    public void rejectReservation(Long reservationId, MentoringReservationRejectRequestDto decisionDto) {
        reservationMapper.updateReservationToRejected(
                reservationId,
                decisionDto.getRejectReason()
        );
    }

    @Override
    public void cancelReservation(Long reservationId, MentoringReservationCancelRequestDto cancelDto) {
        reservationMapper.cancelReservation(
                reservationId,
                cancelDto.getCancelReason()
        );
    }
}