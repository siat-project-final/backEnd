package com.takoyakki.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.takoyakki.backend.domain.challenge.repository.ProblemSolvingMapper;
import com.takoyakki.backend.domain.challenge.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class test {
    private final ChallengeService challengeService;
    private final ProblemSolvingMapper problemSolvingMapper;


    @GetMapping("/test2")
    @ResponseBody
    public ResponseEntity<?> test2() {
        int i = challengeService.insertChallengeProblem("JAVA", 1);
        return ResponseEntity.ok(i + "개의 문제를 추가했습니다.");

    }
}






