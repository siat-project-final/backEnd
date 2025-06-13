package com.takoyakki.backend.domain.mentoring.model;

import lombok.Data;
import java.util.List;
// Mentor.java


@Data
public class Mentor {
    private Long id;
    private String name;
    private String company;
    private String job;
    private String career;
    private String profileImageUrl;
    private List <String> availableDates; 
    //  ["2025-06-20", "2025-06-22"] 대화 가능 일정
}
