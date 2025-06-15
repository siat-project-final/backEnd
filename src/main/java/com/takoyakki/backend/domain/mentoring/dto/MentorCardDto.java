package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorCardDto {
    private Long id;
    private String name;
    private String company;
    private String position;
    private String profileImageUrl; // 멘토 사진 URL
}
