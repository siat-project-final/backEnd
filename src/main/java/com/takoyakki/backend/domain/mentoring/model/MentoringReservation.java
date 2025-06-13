package com.takoyakki.backend.domain.mentoring.model;

import java.util.Date;
import lombok.Data;

@Data
public class MentoringReservation {
    private Long id;
    private String title;
    private String content;
    private String date; // YYYY-MM-DD
    private String time; // HH:mm
    private Long mentorId;
}
