package com.takoyakki.backend.domain.challenge.repository;

import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertItemRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemSolvingInsertRequestDto;
import com.takoyakki.backend.domain.challenge.dto.request.ProblemsInsertRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProblemSolvingMapper {
    int insertProblemSolving(List<ProblemSolvingInsertItemRequestDto> requestDto);
}
