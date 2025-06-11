package com.takoyakki.backend.common.auth.service;

import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.auth.dto.SignUpRequestDto;
import com.takoyakki.backend.common.auth.mapper.AuthMapper;
import com.takoyakki.backend.common.auth.dto.LoginRequestDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        LoginResponseDto loginResponseDto = authMapper.selectUserInfo(request.getId());
        System.out.println("loginResponseDto = " + loginResponseDto);

        if (loginResponseDto == null) {
            throw new UnauthorizedException("해당하는 유저가 존재하지 않습니다.");
        }

        if (!loginResponseDto.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        JwtTokenProvider.TokenInfo tokenInfo = jwtTokenProvider.createToken(loginResponseDto);

        loginResponseDto.setAccessToken(tokenInfo.getAccessToken());
        loginResponseDto.setRefreshToken(tokenInfo.getRefreshToken());
        loginResponseDto.setMessage("로그인 성공");

        return loginResponseDto;
    }

    @Override
    public void logout(String id) {
        jwtTokenProvider.removeRefreshToken(id);
    }

    @Override
    @Transactional
    public int signUp(SignUpRequestDto requestDto) {
        try {
            return authMapper.signUp(requestDto);
        } catch (Exception e) {
            throw new UnauthorizedException("회원가입에 실패했습니다.");
        }
    }
}
