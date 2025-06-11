package com.takoyakki.backend.common.auth;

import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    private final Map<String, String> refreshTokenStore = new HashMap<>();

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String accessToken;
        private String refreshToken;
    }

    public TokenInfo createToken(LoginResponseDto loginResponseDto) {
        // Access Token 생성
        String accessToken = createAccessToken(loginResponseDto);
        // Refresh Token 생성
        String refreshToken = createRefreshToken(loginResponseDto);

        // Refresh Token 저장
        refreshTokenStore.put(loginResponseDto.getId(), refreshToken);

        return new TokenInfo(accessToken, refreshToken);
    }


    public String createAccessToken(LoginResponseDto loginResponseDto) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        Long expTime = 1000 * 60L * 3L;
        Date ext = new Date();
        ext.setTime(ext.getTime() + expTime);

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", loginResponseDto.getId());
        payload.put("memberName", loginResponseDto.getMemberName());
        payload.put("role", loginResponseDto.getRole());

        return Jwts.builder()
                .setHeader(header)
                .setClaims(payload)
                .setSubject("access")
                .setExpiration(ext)
                .signWith(key)
                .compact();
    }

    private String createRefreshToken(LoginResponseDto loginResponseDto) {
        Long refreshExpTime = 1000 * 60L * 60L * 24L * 14L; // 14일
        Date refreshExt = new Date();
        refreshExt.setTime(refreshExt.getTime() + refreshExpTime);

        return Jwts.builder()
                .setSubject(loginResponseDto.getId())
                .setExpiration(refreshExt)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    // 리프레시 토큰으로 새로운 액세스 토큰 발급
//    public String reissueAccessToken(String refreshToken) {
//        try {
//            // 리프레시 토큰 검증
//            Claims claims = Jwts.parser()
//                    .verifyWith((SecretKey) key)
//                    .build()
//                    .parseSignedClaims(refreshToken)
//                    .getPayload();
//
//            String accountId = claims.getSubject();
//
//            // 저장된 리프레시 토큰과 비교
//            String savedRefreshToken = refreshTokenStore.get(accountId);
//            if (savedRefreshToken == null || !savedRefreshToken.equals(refreshToken)) {
//                throw new JwtException("Invalid refresh token");
//            }
//
//            // 새로운 액세스 토큰 생성
//            Member member = memberRepository.findByAccountId(accountId)
//                    .orElseThrow(() -> new JwtException("Member not found")); // 실제로는 DB에서 조회해야 함
//            member.setAccountId(accountId);
//
//            return createAccessToken(member);
//        } catch (JwtException e) {
//            log.error("Error during access token reissue: {}", e.getMessage(), e);
//            throw new JwtException("Failed to reissue access token");
//        }
//    }

    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return (String) claims.get("id");
    }

    public String getMemberName(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return (String) claims.get("memberName");
    }

    @SuppressWarnings("unchecked")
    public List<String> getRole(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Object positionObj = claims.get("role");
        if (positionObj instanceof String) {
            return Collections.singletonList((String) positionObj);
        } else if (positionObj instanceof List) {
            return (List<String>) positionObj;
        }
        return Collections.emptyList();
    }

    public void removeRefreshToken(String id) {
        refreshTokenStore.remove(id);
    }

}