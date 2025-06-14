package com.takoyakki.backend.domain.member.service;

import com.takoyakki.backend.domain.member.dto.MemberSelectResponseDto;
import com.takoyakki.backend.domain.member.dto.MemberUpdateRequestDto;

public interface MemberService {
    MemberSelectResponseDto selectMemberInfo(Long memberId);

    int updateMemberInfo(Long memberId, MemberUpdateRequestDto updateDto);
}
