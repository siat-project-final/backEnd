package com.takoyakki.backend.common.auth.controller;

import com.takoyakki.backend.common.auth.JwtTokenProvider;
import com.takoyakki.backend.common.auth.service.AuthService;
import com.takoyakki.backend.common.auth.dto.LoginRequestDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "인증", description = "인증 관련 API")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(
            summary = "로그인",
            description = "멤버 계정과 비밀번호로 로그인을 수행합니다"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
        LoginResponseDto response = authService.login(request);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + response.getAccessToken())
                .header("Refresh-Token", response.getRefreshToken())
                .body(response);
    }

//    @Operation(
//            summary = "액세스 토큰 만료 체크",
//            description = "현재 액세스 토큰이 만료되었는지 체크합니다"
//    )
//    @PostMapping("/checkAccessToken")
//    public ResponseEntity<?> someEndpoint(@RequestHeader("Authorization") String token) {
//        // 2. API 호출시 액세스 토큰 검증
//        if (!jwtTokenProvider.validateToken(token)) {
//            // 3. 액세스 토큰 만료시 클라이언트는 /reissue 엔드포인트 호출
//            throw new UnauthorizedException("토큰이 만료되었습니다.");
//        }
//        // 정상적인 API 처리
//        return ResponseEntity.ok().build();
//    }
//
//    @Operation(
//            summary = "토큰 재발급",
//            description = "리프레시 토큰을 사용하여 새로운 액세스 토큰을 발급받습니다"
//    )
//    @PostMapping("/reissue")
//    public ResponseEntity<?> reissueToken(@RequestHeader("Refresh-Token") String refreshToken) {
//        String newAccessToken = authService.reissueAccessToken(refreshToken);
//        return ResponseEntity.ok()
//                .header("Authorization", "Bearer " + newAccessToken)
//                .body("토큰이 재발급되었습니다.");
//    }
//
    @Operation(
            summary = "로그아웃",
            description = "사용자 로그아웃을 수행합니다"
    )
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        String id = jwtTokenProvider.getId(token.replace("Bearer ", ""));
        authService.logout(id);
        return ResponseEntity.ok().body("로그아웃 되었습니다.");
    }
}
