package com.takoyakki.backend.domain.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PreConversationDto {
    private MentorCardDto mentorDetail;
    private List<String> availableTopics;
}
// 예약 DTO
