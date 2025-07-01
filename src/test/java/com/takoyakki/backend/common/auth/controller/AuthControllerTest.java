package com.takoyakki.backend.common.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.auth.dto.*;
import com.takoyakki.backend.common.auth.service.AuthService;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void signUp_success() throws Exception {
        SignUpRequestDto dto = SignUpRequestDto.builder()
                .id("hong123")
                .password("password123")
                .memberName("홍길동")
                .phoneNumber("010-1234-5678")
                .email("test@test.com")
                .nickname("hong")
                .build();

        given(authService.signUp(any(SignUpRequestDto.class))).willReturn(1);

        mockMvc.perform(post("/v1/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 성공")
    void login_success() throws Exception {
        LoginRequestDto dto = LoginRequestDto.builder()
                .id("user1")
                .password("password1")
                .build();

        LoginResponseDto response = LoginResponseDto.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();

        given(authService.login(any(LoginRequestDto.class))).willReturn(response);

        mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", "Bearer accessToken"))
                .andExpect(header().string("Refresh-Token", "refreshToken"))
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

    @Test
    @DisplayName("액세스 토큰 만료 체크 - 유효")
    void checkAccessToken_valid() throws Exception {
        given(jwtTokenProvider.validateToken(anyString())).willReturn(true);

        mockMvc.perform(post("/v1/auth/checkAccessToken")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("액세스 토큰 만료 체크 - 만료")
    void checkAccessToken_expired() throws Exception {
        given(jwtTokenProvider.validateToken(anyString())).willReturn(false);

        mockMvc.perform(post("/v1/auth/checkAccessToken")
                        .header("Authorization", "Bearer expiredToken"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("토큰 재발급 성공")
    void reissueToken_success() throws Exception {
        given(authService.reissueAccessToken(anyString())).willReturn("newAccessToken");

        mockMvc.perform(post("/v1/auth/reissue")
                        .header("Refresh-Token", "refreshToken"))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", "Bearer newAccessToken"))
                .andExpect(content().string("토큰이 재발급되었습니다."));
    }

    @Test
    @DisplayName("로그아웃 성공")
    void logout_success() throws Exception {
        given(jwtTokenProvider.getId(anyString())).willReturn("user1");

        mockMvc.perform(post("/v1/auth/logout")
                        .header("Authorization", "Bearer accessToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("로그아웃 되었습니다."));
    }

    @Test
    @DisplayName("회원가입 실패 - 인증 예외")
    void signUp_fail_unauthorized() throws Exception {
        SignUpRequestDto dto = SignUpRequestDto.builder()
                .id("hong123")
                .password("password123")
                .memberName("홍길동")
                .phoneNumber("010-1234-5678")
                .email("test@test.com")
                .nickname("hong")
                .build();

        doThrow(new UnauthorizedException("인증 실패")).when(authService).signUp(any(SignUpRequestDto.class));

        mockMvc.perform(post("/v1/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized());
    }
}
