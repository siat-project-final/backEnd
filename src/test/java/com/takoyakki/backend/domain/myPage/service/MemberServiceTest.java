package com.takoyakki.backend.domain.myPage.service;

import com.takoyakki.backend.common.exception.ResourceNotFoundException;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import com.takoyakki.backend.domain.myPage.dto.MemberSelectResponseDto;
import com.takoyakki.backend.domain.myPage.dto.MemberUpdateRequestDto;
import com.takoyakki.backend.domain.myPage.repository.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("회원 정보 조회 성공")
    void selectMemberInfo_success() {
        // given
        Long memberId = 1L;
        MemberSelectResponseDto mockResponse = MemberSelectResponseDto.builder()
                .memberId(memberId)
                .memberName("홍길동")
                .email("test@test.com")
                .build();
        given(memberMapper.selectMemberInfo(memberId)).willReturn(mockResponse);

        // when
        MemberSelectResponseDto result = memberService.selectMemberInfo(memberId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getMemberId()).isEqualTo(memberId);
        assertThat(result.getMemberName()).isEqualTo("홍길동");
        assertThat(result.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    @DisplayName("회원 정보 조회 실패 - 정보 없음")
    void selectMemberInfo_unauthorized() {
        // given
        Long memberId = 2L;
        given(memberMapper.selectMemberInfo(memberId)).willReturn(null);

        // when & then
        assertThatThrownBy(() -> memberService.selectMemberInfo(memberId))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("회원 정보를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void updateMemberInfo_success() {
        // given
        Long memberId = 1L;
        MemberUpdateRequestDto updateDto = MemberUpdateRequestDto.builder()
                .email("change@test.com")
                .nickname("홍")
                .phoneNumber("010-1111-1111")
                .build();

        MemberSelectResponseDto existMember = MemberSelectResponseDto.builder()
                .memberId(memberId)
                .memberName("홍길동")
                .email("test@test.com")
                .build();

        given(memberMapper.selectMemberInfo(memberId)).willReturn(existMember);
        given(memberMapper.updateMemberInfo(any(MemberUpdateRequestDto.class))).willReturn(1);

        // when
        int result = memberService.updateMemberInfo(memberId, updateDto);

        // then
        assertThat(result).isEqualTo(1);
        assertThat(updateDto.getMemberId()).isEqualTo(memberId);
        verify(memberMapper).updateMemberInfo(updateDto);
    }

    @Test
    @DisplayName("회원 정보 수정 실패 - 존재하지 않는 멤버")
    void updateMemberInfo_fail_noMember() {
        // given
        Long memberId = 99L;
        MemberUpdateRequestDto updateDto = MemberUpdateRequestDto.builder()
                .email("change@test.com")
                .nickname("홍")
                .phoneNumber("010-1111-1111")
                .build();

        given(memberMapper.selectMemberInfo(memberId)).willReturn(null);

        // when & then
        assertThatThrownBy(() -> memberService.updateMemberInfo(memberId, updateDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("존재하지 않는 멤버입니다");
    }
}
