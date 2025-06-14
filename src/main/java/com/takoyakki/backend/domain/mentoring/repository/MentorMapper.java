package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


//멘토 조회용

@Mapper
public interface MentorMapper {
    Mentor findById(Long id);
    List<Mentor> findAllMentors(@Param("offset") int offset, @Param("limit") int limit);
}
