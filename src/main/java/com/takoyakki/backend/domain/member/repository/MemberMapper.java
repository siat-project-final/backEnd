package com.takoyakki.backend.domain.member.repository;

import com.takoyakki.backend.domain.member.dto.MemberSelectResponseDto;
import com.takoyakki.backend.domain.member.dto.MemberUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberSelectResponseDto selectMemberInfo(Long id);

    int updateMemberInfo(MemberUpdateRequestDto updateDto);
}
