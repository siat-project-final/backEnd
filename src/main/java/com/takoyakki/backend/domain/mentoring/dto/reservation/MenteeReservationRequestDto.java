package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@NoArgsConstructor
public class MenteeReservationRequestDto {

    private MentoringReservationAcceptDto mentorDetail;     // 멘토 정보 (카드 형태)
    private List<String> availableTopics;   // 선택 가능한 대화 주제


    public MenteeReservationRequestDto(MentoringReservationAcceptDto mentoringReservationAcceptDto, List<String> topics) {
        this.mentorDetail = new MentoringReservationAcceptDto();
        this.availableTopics = topics;
    }
}
