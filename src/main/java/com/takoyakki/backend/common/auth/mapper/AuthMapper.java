package com.takoyakki.backend.common.auth.mapper;

import com.takoyakki.backend.common.auth.dto.LoginAuthCheckDto;
import com.takoyakki.backend.common.auth.dto.LoginResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AuthMapper {
    LoginAuthCheckDto selectUserInfo(String id);
}
