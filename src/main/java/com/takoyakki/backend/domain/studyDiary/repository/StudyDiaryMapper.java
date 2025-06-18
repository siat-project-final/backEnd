package com.takoyakki.backend.domain.studyDiary.repository;

import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryInsertRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.request.StudyDiaryUpdateRequestDto;
import com.takoyakki.backend.domain.studyDiary.dto.response.StudyDiarySelectResponseDto;
import com.takoyakki.backend.domain.studyDiary.model.StudyDiary; // Model 임포트
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper // MyBatis Mapper 인터페이스임을 선언
public interface StudyDiaryMapper {

    // 학습일지 전체 조회 (로그인한 사용자 본인의 학습일지)
    List<StudyDiarySelectResponseDto> findAllByMemberId(@Param("memberId") Long memberId, @Param("subject") String subject);

    // 학습일지 공개 조회 (모든 공개된 학습일지)
    // is_public 컬럼이 현재 schema.sql에 없으므로, 이 메서드는 현재 상태에서 SQL 쿼리 구현이 어렵습니다.
    // 일단 인터페이스에만 남겨두고, SQL은 비워두거나, schema.sql 업데이트 후 구현합니다.
    List<StudyDiarySelectResponseDto> findAllPublic(@Param("subject") String subject);

    // 특정 멤버의 학습일지 전체 조회
    List<StudyDiarySelectResponseDto> findAllByOtherMemberId(@Param("memberId") Long memberId, @Param("subject") String subject);

    // 학습일지 단건 조회
    Optional<StudyDiarySelectResponseDto> findById(@Param("diaryId") Long diaryId);

    // 학습일지 작성
    // useGeneratedKeys 및 keyProperty는 schema.sql에 IDENTITY가 없으므로 사용하지 않습니다.
    void insertStudyDiary(@Param("dto") StudyDiaryInsertRequestDto dto, @Param("memberId") Long memberId);

    // 학습일지 수정
    int updateStudyDiary(@Param("dto") StudyDiaryUpdateRequestDto dto, @Param("diaryId") Long diaryId, @Param("memberId") Long memberId);

    // 학습일지 삭제 (is_deleted = TRUE)
    int deleteStudyDiary(@Param("diaryId") Long diaryId, @Param("memberId") Long memberId);

    // AI 요약 업데이트
    int updateAiSummary(@Param("diaryId") Long diaryId, @Param("aiSummary") String aiSummary);

    // Member ID와 Diary ID로 해당 학습일지가 존재하는지 확인 (소유권 확인 등)
    // DTO가 아닌 모델(StudyDiary)을 반환하여 DB 레코드 전체 정보를 가져올 수 있도록 합니다.
    Optional<StudyDiary> findByDiaryIdAndMemberId(@Param("diaryId") Long diaryId, @Param("memberId") Long memberId);
}