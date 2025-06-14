package com.takoyakki.backend.domain.mentoring.repository;

import com.takoyakki.backend.domain.mentoring.model.Mentor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

//멘토 조회
@Mapper
public interface MentorMapper {

    //(클릭시) 멘토ID로 상세조회
    Mentor findById(@Param("id") Long id);

    // 전체멘도내역
    List<Mentor> findAllMentors(@Param("offset") int offset, @Param("limit") int limit);

}
