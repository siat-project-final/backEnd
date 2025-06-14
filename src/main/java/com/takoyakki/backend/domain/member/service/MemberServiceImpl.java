package com.takoyakki.backend.domain.member.service;

import com.takoyakki.backend.common.exception.BusinessLogicException;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import com.takoyakki.backend.domain.member.dto.MemberSelectResponseDto;
import com.takoyakki.backend.domain.member.dto.MemberUpdateRequestDto;
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
    public MemberSelectResponseDto selectMemberInfo(Long memberId) {
        return Optional.ofNullable(memberMapper.selectMemberInfo(memberId))
                .orElseThrow(() -> new UnauthorizedException("회원 정보를 찾을 수 없습니다."));
    }

    @Override
    public int updateMemberInfo(Long memberId, MemberUpdateRequestDto updateDto) {
        updateDto.setMemberId(memberId);
        MemberSelectResponseDto memberSelectResponseDto = memberMapper.selectMemberInfo(memberId);
        if (memberSelectResponseDto == null) {
            throw new BusinessLogicException("존재하지 않는 멤버입니다");
        }

        return memberMapper.updateMemberInfo(updateDto);
    }
}
