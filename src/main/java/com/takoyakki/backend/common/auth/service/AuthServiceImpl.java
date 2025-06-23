package com.takoyakki.backend.common.auth.service;


import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.auth.dto.*;
import com.takoyakki.backend.common.auth.mapper.AuthMapper;
import com.takoyakki.backend.common.exception.TokenExpiredException;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        System.out.println("authMapper.selectUserInfo(request.getId() = " + authMapper.selectUserInfo(request.getId()));

        LoginAuthCheckDto loginAuthCheckDto = Optional.ofNullable(authMapper.selectUserInfo(request.getId()))
                .orElseThrow(() -> new UnauthorizedException("해당하는 유저가 존재하지 않습니다."));

        if (!loginAuthCheckDto.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        JwtTokenProvider.TokenInfo tokenInfo = jwtTokenProvider.createToken(loginAuthCheckDto);

        return LoginResponseDto.builder()
                .id(loginAuthCheckDto.getId())
                .memberId(loginAuthCheckDto.getMemberId())
                .memberName(loginAuthCheckDto.getMemberName())
                .role(loginAuthCheckDto.getRole())
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .message("로그인 성공")
                .build();
    }

    public String reissueAccessToken(String refreshToken) {
        return jwtTokenProvider.reissueAccessToken(refreshToken);
    }

    @Override
    public void logout(String id) {
        jwtTokenProvider.removeRefreshToken(id);
    }

    @Override
    @Transactional
    public int signUp(SignUpRequestDto requestDto) {
        // 1 명단 존재 체크
        int registeredYn = checkStudentList(requestDto);

        if (registeredYn == 0) {
            throw new UnauthorizedException("인증 실패 : 명단에 등록되지 않은 학생입니다.");
        }

        // 2 중복 회원가입 체크
        SignUpDuplicationCheckDto signUpDuplicationCheckDto = authMapper.checkSignUpDuplication(requestDto.getMemberName(), requestDto.getPhoneNumber());

        if (signUpDuplicationCheckDto != null) {
            throw new UnauthorizedException("이미 가입된 이름이거나 전화번호입니다.");
        }

        try {
            return authMapper.signUp(requestDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException("회원가입에 실패했습니다.");
        }
    }

    @Override
    public int checkStudentList(SignUpRequestDto requestDto) {
        int retVal = authMapper.selectStudentInfo(requestDto.getMemberName(), requestDto.getPhoneNumber());
        return Math.max(retVal, 0);
    }
}
