package com.takoyakki.backend.domain.challenge.scheduler;

import com.takoyakki.backend.common.exception.ResourceNotFoundException;
import com.takoyakki.backend.domain.challenge.service.ChallengeService;
import com.takoyakki.backend.domain.dailyLearning.repository.DailyLearningMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChallengeScheduler {
    private final DailyLearningMapper dailyLearningMapper;
    private final ChallengeService challengeService;

    @Scheduled(cron = "0 51 9 * * *") // 매일 오전 7시
    public void createDailyChallengeProblems() {
        log.info("챌린지 문제 생성 스케줄러 실행 시작: {}", LocalDateTime.now());

        // 챌린지 문제 생성 로직
        try {
            List<String> subjects = null;
            LocalDate searchDate = LocalDate.now();
            int maxDays = 30;

            for (int i = 0; i < maxDays; i++) {
                subjects = dailyLearningMapper.selectDailyLearning(searchDate);
                if (subjects != null && !subjects.isEmpty()) {
                    log.info("해당 날짜에 등록된 학습 주제가 존재하지 않습니다.`: {}", searchDate);
                    break;
                }
                searchDate = searchDate.minusDays(1);
            }

            if (subjects == null || subjects.isEmpty()) {
                throw new ResourceNotFoundException("최근 " + maxDays + "일 내에 학습 데이터가 존재하지 않습니다.");
            }

            // 난이도 1~5 문제 생성
            for (int i = 1; i <= 5; i++) {
                challengeService.insertChallengeProblem(subjects.get(0), i);
            }

        } catch (Exception e) {
            log.error("챌린지 문제 생성 중 오류 발생: {}", e.getMessage(), e);
        }

        log.info("챌린지 문제 생성 스케줄러 실행 종료: {}", LocalDateTime.now());
    }
}