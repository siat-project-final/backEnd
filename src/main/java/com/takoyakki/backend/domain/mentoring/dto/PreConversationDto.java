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

    private MentorDto mentorDetail;     // 멘토 정보 (카드 형태)
    private List<String> availableTopics;   // 선택 가능한 대화 주제


    public PreConversationDto(Mentor mentorDto, List<String> topics) {
        this.mentorDetail = new MentorDto();
        this.availableTopics = topics;
    }
}
