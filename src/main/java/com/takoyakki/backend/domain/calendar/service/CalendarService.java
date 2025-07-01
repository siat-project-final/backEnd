package com.takoyakki.backend.domain.calendar.service;

import com.takoyakki.backend.domain.calendar.dto.response.CalendarScheduleListResponseDto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

public interface CalendarService {
    Map<LocalDate, CalendarScheduleListResponseDto> selectScheduleAllByMonth(Long memberId, YearMonth month);
}
