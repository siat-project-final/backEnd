package com.takoyakki.backend.domain.studyDiary.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyDiary {
    private Long diaryId; // DB의 diary_id 컬럼과 매핑 (현재 schema.sql에 PRIMARY KEY/IDENTITY 없음)
    private Long memberId; // DB의 member_id 컬럼과 매핑
    private String contents; // DB의 contents 컬럼과 매핑
    private String title; // DB의 title 컬럼과 매핑
    private String subject; // DB의 subject 컬럼과 매핑
    private LocalDateTime createdAt; // DB의 created_at 컬럼과 매핑
    private LocalDateTime updatedAt; // DB의 updated_at 컬럼과 매핑
    private String aiSummary; // DB의 AI_summary 컬럼과 매핑
    private Boolean isDeleted; // DB의 is_deleted 컬럼과 매핑
    private Boolean isPublic; // DB에 현재 없음 (추후 is_public 컬럼 추가 시 사용)
}
