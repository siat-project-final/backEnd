package com.takoyakki.backend.domain.mentoring.dto.reservation;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
public class MenteeReservationRequestDto {
    private Long mentorId;
    private Long menteeId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    private String subject;
    private String introduction;
    private String etcMessage;
}


