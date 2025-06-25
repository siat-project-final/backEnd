package com.takoyakki.backend.domain.notification.service;

import com.takoyakki.backend.domain.notification.dto.response.NotificationToMenteeSelectResponseDto;
import com.takoyakki.backend.domain.notification.dto.response.NotificationToMentorSelectResponseDto;
import com.takoyakki.backend.domain.notification.repository.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService{
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationToMentorSelectResponseDto> selectNotificationToMentor(Long memberId) {
        return List.of();
    }

    @Override
    public List<NotificationToMenteeSelectResponseDto> selectNotificationToMentee(Long memberId) {
        return List.of();
    }
}
