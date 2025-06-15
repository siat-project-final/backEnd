package com.takoyakki.backend.domain.mentoring.dto;

import com.takoyakki.backend.domain.mentoring.model.Mentor;
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


    public PreConversationDto(Mentor mentor, List<String> topics) {
    }
}
// 예약 DTO
