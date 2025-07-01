package com.takoyakki.backend.common.auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.auth.dto.*;
import com.takoyakki.backend.common.auth.mapper.AuthMapper;
import com.takoyakki.backend.common.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService 테스트")
class AuthServiceTest {
    @Mock
    private AuthMapper authMapper;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthServiceImpl authService;

    private LoginRequestDto loginRequestDto;
    private LoginAuthCheckDto loginAuthCheckDto;
    private SignUpRequestDto signUpRequestDto;
    private JwtTokenProvider.TokenInfo tokenInfo;

    @BeforeEach
    void setUp() {
        loginRequestDto = LoginRequestDto.builder()
                .id("testUser")
                .password("testPassword")
                .build();

        loginAuthCheckDto = LoginAuthCheckDto.builder()
                .id("testUser")
                .password("testPassword")
                .memberName("테스트유저")
                .role("USER")
                .build();

        signUpRequestDto = SignUpRequestDto.builder()
                .memberName("신규유저")
                .phoneNumber("010-1234-5678")
                .build();

        tokenInfo = JwtTokenProvider.TokenInfo.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void 로그인_성공() {
        // given
        given(authMapper.selectUserInfo(loginRequestDto.getId()))
                .willReturn(loginAuthCheckDto);
        given(jwtTokenProvider.createToken(loginAuthCheckDto))
                .willReturn(tokenInfo);

        // when
        LoginResponseDto result = authService.login(loginRequestDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("testUser");
        assertThat(result.getMemberName()).isEqualTo("테스트유저");
        assertThat(result.getRole()).isEqualTo("USER");
        assertThat(result.getAccessToken()).isEqualTo("accessToken");
        assertThat(result.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(result.getMessage()).isEqualTo("로그인 성공");

        verify(authMapper).selectUserInfo(loginRequestDto.getId());
        verify(jwtTokenProvider).createToken(loginAuthCheckDto);
    }

    @Test
    @DisplayName("로그인 실패 - 존재하지 않는 유저")
    void 로그인_실패_존재하지않는유저() {
        // given
        given(authMapper.selectUserInfo(loginRequestDto.getId()))
                .willReturn(null);

        // when & then
        assertThatThrownBy(() -> authService.login(loginRequestDto))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("해당하는 유저가 존재하지 않습니다.");

        verify(authMapper).selectUserInfo(loginRequestDto.getId());
        verify(jwtTokenProvider, never()).createToken(any());
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void 로그인_실패_비밀번호불일치() {
        // given
        LoginAuthCheckDto wrongPasswordUser = LoginAuthCheckDto.builder()
                .id("testUser")
                .password("wrongPassword")
                .memberName("테스트유저")
                .role("USER")
                .build();

        given(authMapper.selectUserInfo(loginRequestDto.getId()))
                .willReturn(wrongPasswordUser);

        // when & then
        assertThatThrownBy(() -> authService.login(loginRequestDto))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("비밀번호가 일치하지 않습니다.");

        verify(authMapper).selectUserInfo(loginRequestDto.getId());
        verify(jwtTokenProvider, never()).createToken(any());
    }

    @Test
    @DisplayName("액세스 토큰 재발급 테스트")
    void 액세스토큰_재발급() {
        // given
        String refreshToken = "refreshToken";
        String newAccessToken = "newAccessToken";
        given(jwtTokenProvider.reissueAccessToken(refreshToken))
                .willReturn(newAccessToken);

        // when
        String result = authService.reissueAccessToken(refreshToken);

        // then
        assertThat(result).isEqualTo(newAccessToken);
        verify(jwtTokenProvider).reissueAccessToken(refreshToken);
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void 로그아웃() {
        // given
        String userId = "testUser";

        // when
        authService.logout(userId);

        // then
        verify(jwtTokenProvider).removeRefreshToken(userId);
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void 회원가입_성공() {
        // given
        given(authMapper.selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(1);
        given(authMapper.checkSignUpDuplication(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(null);
        given(authMapper.signUp(signUpRequestDto))
                .willReturn(1);

        // when
        int result = authService.signUp(signUpRequestDto);

        // then
        assertThat(result).isEqualTo(1);
        verify(authMapper).selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper).checkSignUpDuplication(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper).signUp(signUpRequestDto);
    }

    @Test
    @DisplayName("회원가입 실패 - 명단에 등록되지 않은 학생")
    void 회원가입_실패_미등록학생() {
        // given
        given(authMapper.selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(0);

        // when & then
        assertThatThrownBy(() -> authService.signUp(signUpRequestDto))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("인증 실패 : 명단에 등록되지 않은 학생입니다.");

        verify(authMapper).selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper, never()).checkSignUpDuplication(any(), any());
        verify(authMapper, never()).signUp(any());
    }

    @Test
    @DisplayName("회원가입 실패 - 중복된 회원정보")
    void 회원가입_실패_중복회원() {
        // given
        SignUpDuplicationCheckDto duplicateDto = SignUpDuplicationCheckDto.builder().build();
        given(authMapper.selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(1);
        given(authMapper.checkSignUpDuplication(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(duplicateDto);

        // when & then
        assertThatThrownBy(() -> authService.signUp(signUpRequestDto))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("이미 가입된 이름이거나 전화번호입니다.");

        verify(authMapper).selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper).checkSignUpDuplication(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper, never()).signUp(any());
    }

    @Test
    @DisplayName("회원가입 실패 - DB 예외 발생")
    void 회원가입_실패_DB예외() {
        // given
        given(authMapper.selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(1);
        given(authMapper.checkSignUpDuplication(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(null);
        given(authMapper.signUp(signUpRequestDto))
                .willThrow(new RuntimeException("DB 오류"));

        // when & then
        assertThatThrownBy(() -> authService.signUp(signUpRequestDto))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("회원가입에 실패했습니다.");

        verify(authMapper).selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper).checkSignUpDuplication(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
        verify(authMapper).signUp(signUpRequestDto);
    }

    @Test
    @DisplayName("학생 명단 확인 테스트 - 등록된 학생")
    void 학생명단확인_등록된학생() {
        // given
        given(authMapper.selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(1);

        // when
        int result = authService.checkStudentList(signUpRequestDto);

        // then
        assertThat(result).isEqualTo(1);
        verify(authMapper).selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
    }

    @Test
    @DisplayName("학생 명단 확인 테스트 - 미등록 학생")
    void 학생명단확인_미등록학생() {
        // given
        given(authMapper.selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber()))
                .willReturn(-1);

        // when
        int result = authService.checkStudentList(signUpRequestDto);

        // then
        assertThat(result).isEqualTo(0); // Math.max(-1, 0) = 0
        verify(authMapper).selectStudentInfo(signUpRequestDto.getMemberName(), signUpRequestDto.getPhoneNumber());
    }

}