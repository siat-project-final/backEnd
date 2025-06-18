package com.takoyakki.backend.domain.myPage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.exception.ResourceNotFoundException;
import com.takoyakki.backend.domain.myPage.dto.MemberSelectResponseDto;
import com.takoyakki.backend.domain.myPage.dto.MemberUpdateRequestDto;
import com.takoyakki.backend.domain.myPage.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("내 정보 조회 성공")
    void selectMemberInfo_success() throws Exception {
        Long memberId = 1L;
        MemberSelectResponseDto responseDto = MemberSelectResponseDto.builder()
                .memberId(memberId)
                .memberName("홍길동")
                .email("test@test.com")
                .build();

        given(memberService.selectMemberInfo(memberId)).willReturn(responseDto);

        mockMvc.perform(get("/v1/members/{memberId}", memberId))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/v1/members/" + memberId)))
                .andExpect(jsonPath("$.memberId").value(memberId))
                .andExpect(jsonPath("$.memberName").value("홍길동"))
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void updateMember_success() throws Exception {
        Long memberId = 1L;
        MemberUpdateRequestDto updateDto = MemberUpdateRequestDto.builder()
                .nickname("변경된 이름")
                .email("change@test.com")
                .phoneNumber("010-1234-5678")
                .build();

        given(memberService.updateMemberInfo(eq(memberId), any(MemberUpdateRequestDto.class))).willReturn(1);

        mockMvc.perform(put("/v1/members/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("회원 정보 수정 성공"));
    }

    @Test
    @DisplayName("회원 정보 수정 실패 - 존재하지 않는 회원")
    void updateMember_fail_notFound() throws Exception {
        Long memberId = 999L;
        MemberUpdateRequestDto updateDto = MemberUpdateRequestDto.builder()
                .nickname("변경된 이름")
                .email("change@test.com")
                .phoneNumber("010-1234-5678")
                .build();

        given(memberService.updateMemberInfo(eq(memberId), any(MemberUpdateRequestDto.class)))
                .willThrow(new ResourceNotFoundException("존재하지 않는 멤버입니다"));

        mockMvc.perform(put("/v1/members/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("존재하지 않는 멤버입니다"));
    }

    @Test
    @DisplayName("회원 정보 수정 실패 - 유효성 검증 실패")
    void updateMember_fail_validation() throws Exception {
        Long memberId = 1L;
        MemberUpdateRequestDto updateDto = MemberUpdateRequestDto.builder()
                .phoneNumber("")  // 빈 값
                .email("invalid-email") // 이메일 형식 오류
                .build();

        mockMvc.perform(put("/v1/members/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isBadRequest());
    }
}
