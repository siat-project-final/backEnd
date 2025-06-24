package com.takoyakki.backend.domain.notification.repository;

import com.takoyakki.backend.domain.notification.dto.NotificationChallengeToMenteeDto;
import com.takoyakki.backend.domain.notification.dto.NotificationMentoringReservationToMenteeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {
    // 멘티
    int insertNotificationMentoringReservationToMentee(Long memberId, Long reservationId, NotificationMentoringReservationToMenteeDto dto);

    int insertCancelNotificationMentoringReservationToMentee(Long memberId, Long reservationId, NotificationMentoringReservationToMenteeDto dto);

    // 멘토

    int insertCancelNotificationMentoringReservationToMentor(Long memberId, Long reservationId, NotificationMentoringReservationToMenteeDto dto);


    // 챌린지
    int insertNotificationChallengeRankPointsToMentee(NotificationChallengeToMenteeDto dto);
}
