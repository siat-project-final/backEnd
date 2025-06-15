package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {
    private Long id;
    private String name;
    private String company;
    private String job;
    private String career;
    private String profileImageUrl;       // 멘토 사진 URL
    private List<String> availableDates;  // ["2025-06-20", "2025-06-22"]
}
