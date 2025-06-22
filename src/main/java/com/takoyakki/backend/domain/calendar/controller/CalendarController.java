package com.takoyakki.backend.domain.calendar.controller;

import com.takoyakki.backend.domain.calendar.service.CalendarService;
import com.takoyakki.backend.domain.challenge.dto.response.ProblemsSelectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/calendar")
@Tag(name = "캘린더", description = "랜딩 캘린더 데이터 관리 API")
public class CalendarController {
    private final CalendarService calendarService;

    @Operation(
            summary = "멤버의 일정 전체 조회",
            description = "멤버의 일정 전체를 조회합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "멤버의 일정 전체 조회 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/schedule/{memberId}/{month}")
    public ResponseEntity<?> selectScheduleAllByMonth(@PathVariable("memberId") Long memberId, @PathVariable("month") String month ){
        YearMonth yearMonth = YearMonth.parse(month);
        return ResponseEntity.ok(calendarService.selectScheduleAllByMonth(memberId, yearMonth));
    }

}
