package com.takoyakki.backend.domain.user.service;

import com.takoyakki.backend.common.auth.dto.LoginRequestDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;

public interface UserService {
    LoginResponseDto login(LoginRequestDto request);
}
