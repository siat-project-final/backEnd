package com.takoyakki.backend.domain.challenge.repository;

import com.takoyakki.backend.domain.challenge.dto.request.ProblemsInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.response.ProblemsSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProblemsMapper {
    int insertProblem(ProblemsInsertRequestDto requestDto);

    ProblemsSelectResponseDto selectProblem(Long problemId);


}
