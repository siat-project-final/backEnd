package com.takoyakki.backend;

import com.takoyakki.backend.common.auth.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class test {
    private final AuthMapper authMapper;

//    @GetMapping("/test")
//    public String index() {
//        System.out.println(authMapper.selectUserInfo());
//        return "1";
//    }
}
