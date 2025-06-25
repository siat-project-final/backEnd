package com.takoyakki.backend.domain.mentoring.service;

import com.takoyakki.backend.domain.mentoring.dto.reservation.*;
import com.takoyakki.backend.domain.mentoring.repository.MentoringReservationMapper;
import com.takoyakki.backend.domain.notification.repository.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringReservationServiceImpl implements MentoringReservationService {

    private final MentoringReservationMapper reservationMapper;
    private final NotificationMapper notificationMapper;

    @Override
    public void createReservation(MenteeReservationRequestDto requestDto) {
        reservationMapper.insertReservation(requestDto);
        String contents = requestDto.getMenteeName() + "님께서 " +
                requestDto.getDate().toLocalDate() + "에 멘토링을 신청하셨습니다. " +
                "멘티 자기소개 : " + requestDto.getIntroduction() + " " +
                "주제 : " + requestDto.getSubject() + "입니다. 수락하시겠습니까?";
        requestDto.setNotificationContents(contents);
        notificationMapper.insertNotificationMentoringReservationToMentor(requestDto);
    }

    @Override
    public List<MentoringReservationResponseDto> getReservationsByMenteeId(Long menteeId) {
        return reservationMapper.selectReservationsByMenteeId(menteeId);
    }

    @Override
    public List<MentoringReservationResponseDto> getReservationsByMentorId(Long mentorId) {
        return reservationMapper.selectReservationsByMentorId(mentorId);
    }

    @Override
    public void acceptReservation(Long reservationId) {
        reservationMapper.updateReservationToAccepted(reservationId);
        MentoringReservationResponseDto responseDto = reservationMapper.selectMentoringReservationInfoById(reservationId);
        String contents = responseDto.getMenteeName() + "님, " +
                responseDto.getDate() + "에 예약된" +
                responseDto.getMentorName() + "님과의 멘토링이 확정되었습니다. " +
                responseDto.getOpenChatUrl() + " 이 링크의 오픈채팅방에 참여해주세요.";
        notificationMapper.insertAcceptNotificationMentoringReservationToMentee(responseDto.getMenteeId(), reservationId, contents);


    }

    @Override
    public void rejectReservation(Long reservationId, MentoringReservationRejectRequestDto decisionDto) {
        reservationMapper.updateReservationToRejected(
                reservationId,
                decisionDto.getRejectReason()
        );

        MentoringReservationResponseDto responseDto = reservationMapper.selectMentoringReservationInfoById(reservationId);
        String contents = responseDto.getMenteeName() + "님, " +
                responseDto.getDate().toLocalDate() + "에 예약된 " +
                responseDto.getMentorName() + "님과의 멘토링이 거절되었습니다. " +
                "거절 사유: " + (decisionDto.getRejectReason());
        notificationMapper.insertRejectNotificationMentoringReservationToMentee(responseDto.getMenteeId(), reservationId, contents);

    }

    @Override
    public void cancelReservation(Long reservationId, MentoringReservationCancelRequestDto cancelDto) {
        reservationMapper.cancelReservation(
                reservationId,
                cancelDto.getCancelReason()
        );

        MentoringReservationResponseDto responseDto = reservationMapper.selectMentoringReservationInfoById(reservationId);
        String contents = responseDto.getMentorName() + "님, " +
                responseDto.getDate().toLocalDate() + "에 예약된 " +
                responseDto.getMentorName() + "님과의 멘토링이 취소되었습니다. " +
                "취소 사유: " + (cancelDto.getCancelReason());
        notificationMapper.insertCancelNotificationMentoringReservationToMentor(responseDto.getMentorId(), reservationId, contents);

    }
}
