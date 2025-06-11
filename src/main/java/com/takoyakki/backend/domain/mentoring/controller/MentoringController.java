package com.takoyakki.backend.domain.mentoring.controller;

import com.takoyakki.backend.domain.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MentoringController {
    private final MentoringService mentoringService;
}
