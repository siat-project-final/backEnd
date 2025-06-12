package com.takoyakki.backend.common.auth.mapper;

import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import com.takoyakki.backend.common.auth.dto.SignUpAuthCheckDto;
import com.takoyakki.backend.common.auth.dto.SignUpRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AuthMapper {
    LoginResponseDto selectUserInfo(String id);

    int signUp(SignUpRequestDto requestDto);

    int selectStudentInfo(String memberName, String phoneNumber);
}
