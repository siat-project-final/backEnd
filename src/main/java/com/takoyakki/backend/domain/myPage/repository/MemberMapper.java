package com.takoyakki.backend.domain.myPage.repository;

import com.takoyakki.backend.domain.myPage.dto.MemberSelectResponseDto;
import com.takoyakki.backend.domain.myPage.dto.MemberUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberSelectResponseDto selectMemberInfo(Long id);

    int updateMemberInfo(MemberUpdateRequestDto updateDto);
}
