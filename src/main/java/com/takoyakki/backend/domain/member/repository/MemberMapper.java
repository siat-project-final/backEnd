package com.takoyakki.backend.domain.member.repository;

import com.takoyakki.backend.common.auth.dto.LoginRequestDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import com.takoyakki.backend.domain.member.dto.MemberResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberResponseDto selectMemberInfo(String id);
}
