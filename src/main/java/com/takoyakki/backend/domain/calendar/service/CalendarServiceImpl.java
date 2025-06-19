package com.takoyakki.backend.domain.calendar.service;

import com.takoyakki.backend.domain.calendar.dto.response.CalendarScheduleListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarServiceImpl implements CalendarService{
    @Override
    public Map<LocalDate, CalendarScheduleListResponseDto> selectScheduleAllByMonth() {
        return Map.of();
    }
}
