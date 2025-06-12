package com.takoyakki.backend.common.auth.service;

import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.auth.dto.LoginAuthCheckDto;
import com.takoyakki.backend.common.auth.mapper.AuthMapper;
import com.takoyakki.backend.common.auth.dto.LoginRequestDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        LoginAuthCheckDto loginAuthCheckDto = Optional.ofNullable(authMapper.selectUserInfo(request.getId()))
                .orElseThrow(() -> new UnauthorizedException("해당하는 유저가 존재하지 않습니다."));


        if (!loginAuthCheckDto.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        JwtTokenProvider.TokenInfo tokenInfo = jwtTokenProvider.createToken(loginAuthCheckDto);

        return LoginResponseDto.builder()
                .id(loginAuthCheckDto.getId())
                .memberName(loginAuthCheckDto.getMemberName())
                .role(loginAuthCheckDto.getRole())
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .message("로그인 성공")
                .build();
    }

    @Override
    public void logout(String id) {
        jwtTokenProvider.removeRefreshToken(id);
    }
}
