package com.takoyakki.backend.domain.calendar.service;

import com.takoyakki.backend.domain.calendar.dto.response.CalendarItemCurriculumByDateDto;
import com.takoyakki.backend.domain.calendar.dto.response.CalendarItemMentoringByDateDto;
import com.takoyakki.backend.domain.calendar.dto.response.CalendarScheduleListResponseDto;
import com.takoyakki.backend.domain.dailyLearning.repository.DailyLearningMapper;
import com.takoyakki.backend.domain.mentoring.repository.MentoringMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarServiceImpl implements CalendarService{
    private final MentoringMapper mentoringMapper;
    private final DailyLearningMapper dailyLearningMapper;

    @Override
    public Map<LocalDate, CalendarScheduleListResponseDto> selectScheduleAllByMonth(Long memberId, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();

        Map<LocalDate, CalendarScheduleListResponseDto> resultMap = new LinkedHashMap<>();

        for (int i = 1; i <= month.lengthOfMonth(); i++) {
            LocalDate date = month.atDay(i);
            resultMap.put(date, new CalendarScheduleListResponseDto(date));
        }

        List<CalendarItemMentoringByDateDto> mentorings = mentoringMapper.selectMentoringListInMonthByMemberId(memberId, startDate, endDate);
        List<CalendarItemCurriculumByDateDto> curriculums = dailyLearningMapper.selectCurriculumInMonthByMemberId(startDate, endDate);

        for (CalendarItemMentoringByDateDto m : mentorings) {
            resultMap.get(m.getDate()).getMentoringList().add(m);
        }

        for (CalendarItemCurriculumByDateDto c : curriculums) {
            resultMap.get(c.getDate()).getSubjectList().add(c.getSubject());
        }

        return resultMap;
    }
}
