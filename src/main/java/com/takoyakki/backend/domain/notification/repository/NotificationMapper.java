package com.takoyakki.backend.domain.notification.repository;

import com.takoyakki.backend.domain.notification.dto.NotificationChallengeToMenteeDto;
import com.takoyakki.backend.domain.notification.dto.NotificationMentoringReservationToMenteeDto;
import com.takoyakki.backend.domain.notification.dto.response.NotificationToMenteeSelectResponseDto;
import com.takoyakki.backend.domain.notification.dto.response.NotificationToMentorSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    // 멘티
    int insertNotificationMentoringReservationToMentee(Long memberId, Long reservationId, NotificationMentoringReservationToMenteeDto dto);

    int insertCancelNotificationMentoringReservationToMentee(Long memberId, Long reservationId, NotificationMentoringReservationToMenteeDto dto);

    List<NotificationToMenteeSelectResponseDto> selectNotificationToMentee(Long memberId);

    // 멘토
    int insertCancelNotificationMentoringReservationToMentor(Long memberId, Long reservationId, NotificationMentoringReservationToMenteeDto dto);

    List<NotificationToMentorSelectResponseDto> selectNotificationToMentor(Long memberId);

    // 챌린지
    int insertNotificationChallengeRankPointsToMentee(NotificationChallengeToMenteeDto dto);
}
