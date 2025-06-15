package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.dto.MentorDto;
import com.takoyakki.backend.domain.mentoring.dto.MentoringRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MentorMapper {

    MentorDto findById(@Param("mentorId") Long mentorId);
//클릭 시 상세 멘토 조회
    List<MentorDto> findAllMentors(@Param("offset") int offset, @Param("limit") int limit);

    List<String> findConversationTopicsByMentorId(@Param("mentorId") Long mentorId);

}
