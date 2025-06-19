package com.takoyakki.backend.domain.dailyLearning.repository;

import com.takoyakki.backend.domain.challenge.dto.response.ChallengeReviewSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DailyLearningMapper {
    List<String> selectDailyLearning(LocalDate date);

    List<ChallengeReviewSelectResponseDto> selectDailyLearningProgress();
}
