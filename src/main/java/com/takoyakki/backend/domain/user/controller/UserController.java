package com.takoyakki.backend.domain.user.controller;

import com.takoyakki.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        System.out.println(userService.test());
        return userService.test();
    }
}
