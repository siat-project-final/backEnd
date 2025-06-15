package com.takoyakki.backend.domain.challenge.repository;

import com.takoyakki.backend.domain.challenge.dto.ProblemsInsertRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProblemsMapper {
    int insertProblem(ProblemsInsertRequestDto requestDto);
}
