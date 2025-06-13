package com.takoyakki.backend.common.interceptor;

import com.takoyakki.backend.common.auth.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // OPTIONS 요청은 통과
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }

        // 컨트롤러 메서드가 아닌 경우는 패스
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        /*
        인증
         */

        // 토큰 추출
        String token = jwtTokenProvider.extractToken(request);
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 없습니다.");
            return false;
        }

        // 토큰 검증
        if (!jwtTokenProvider.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다.");
            return false;
        }

        return true;
    }
}
