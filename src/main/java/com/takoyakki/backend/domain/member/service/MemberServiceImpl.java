package com.takoyakki.backend.domain.member.service;

import com.takoyakki.backend.common.auth.dto.LoginRequestDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import com.takoyakki.backend.domain.member.dto.MemberResponseDto;
import com.takoyakki.backend.domain.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    public MemberResponseDto selectMemberInfo(String id) {
        return Optional.ofNullable(memberMapper.selectMemberInfo(id))
                .orElseThrow(() -> new UnauthorizedException("회원 정보를 찾을 수 없습니다."));
    }
}
