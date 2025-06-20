package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.reservation.*;

import java.util.List;

public interface MentoringReservationService {

    void createReservation(MemberReservationRequestDto requestDto);

    List<MentoringReservationResponseDto> getReservationsByMemberId(Long memberId);

    List<MentoringReservationResponseDto> getReservationsByMentorId(Long mentorId);

    void acceptReservation(Long reservationId);

    void rejectReservation(Long reservationId, MentoringReservationRejectRequestDto decisionDto);

    void cancelReservation(Long reservationId, MentoringReservationCancelRequestDto cancelDto);
}
