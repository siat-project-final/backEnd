package com.takoyakki.backend.domain.user.service;

import com.takoyakki.backend.domain.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;

    @Override
    public String test() {
        return userMapper.test();
    }
}
