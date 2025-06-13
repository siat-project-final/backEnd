package com.takoyakki.backend.domain.member.service;

import com.takoyakki.backend.domain.member.dto.MemberResponseDto;

public interface MemberService {
    MemberResponseDto selectMemberInfo(String id);
}
