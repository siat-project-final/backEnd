package com.takoyakki.backend.domain.mentoring.dto;

import com.takoyakki.backend.domain.mentoring.model.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorCardDto {
    private Long id;
    private String name;
    private String company;
    private String job;
    private String career;
    private String phone;
    private String profileImageUrl;    // 멘토 사진 URL
    private List<String> availableDates; // ["2025-06-20", "2025-06-22"]

    // Mentor -> MentorCardDto 변환 생성자
    public MentorCardDto(Mentor mentor) {
        this.id = mentor.getId();
        this.name = mentor.getName();
        this.company = mentor.getCompany();
        this.job = mentor.getJob();
        this.career = mentor.getCareer();
        this.profileImageUrl = mentor.getProfileImageUrl();
        this.availableDates = mentor.getAvailableDates();
    }
}
