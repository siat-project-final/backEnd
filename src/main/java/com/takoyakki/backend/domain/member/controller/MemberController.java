package com.takoyakki.backend.domain.member.controller;

import com.takoyakki.backend.domain.member.dto.MemberResponseDto;
import com.takoyakki.backend.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@Tag(name = "회원 관리", description = "내 정보 조회, 수정, 탈퇴 등 회원관리 API")
public class MemberController {
    private final MemberService memberService;

    @Operation(
            summary = "내 정보 조회",
            description = "멤버 자신의 정보를 조회합니다",
            tags = { "member" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestParam String id){
        MemberResponseDto memberResponseDto = memberService.selectMemberInfo(id);
        Long memberId = memberResponseDto.getMemberId();
        return ResponseEntity
                .created(URI.create("/v1/members/" + memberId))
                .body(memberResponseDto);
    }



}
