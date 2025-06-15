package com.takoyakki.backend.domain.member.controller;

import com.takoyakki.backend.domain.member.dto.MemberRequestDto;
import com.takoyakki.backend.domain.member.model.Member;
import com.takoyakki.backend.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리 API", description = "회원 가입, 조회, 정보 수정 등을 처리합니다.")
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // --- POST /api/members/register : 회원 가입 ---
    @Operation(summary = "회원 가입", description = "새로운 회원을 등록합니다.",
               responses = {
                       @ApiResponse(responseCode = "201", description = "회원 가입 성공",
                                    content = @Content(schema = @Schema(implementation = Member.class))),
                       @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터 또는 중복된 ID/이메일/닉네임")
               })
    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody MemberRequestDto requestDto) {
        try {
            Member newMember = memberService.registerMember(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 실제로는 에러 메시지를 포함하는 DTO 반환 권장
        }
    }

    // --- GET /api/members/{memberId} : 회원 정보 조회 ---
    @Operation(summary = "특정 회원 정보 조회", description = "memberId로 회원 정보를 조회합니다.",
               responses = {
                       @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공",
                                    content = @Content(schema = @Schema(implementation = Member.class))),
                       @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
               })
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberById(
            @Parameter(description = "조회할 회원의 memberId", required = true, example = "1")
            @PathVariable Long memberId) {
        return memberService.findMemberById(memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- PUT /api/members/{memberId} : 회원 정보 업데이트 ---
    @Operation(summary = "회원 정보 업데이트", description = "memberId로 회원 정보를 업데이트합니다.",
               responses = {
                       @ApiResponse(responseCode = "200", description = "회원 정보 업데이트 성공",
                                    content = @Content(schema = @Schema(implementation = Member.class))),
                       @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
                       @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
               })
    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(
            @Parameter(description = "업데이트할 회원의 memberId", required = true, example = "1")
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto requestDto) {
        try {
            memberService.updateMember(memberId, requestDto);
            // 업데이트된 회원을 다시 조회하여 반환
            return memberService.findMemberById(memberId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // --- DELETE /api/members/{memberId} : 회원 탈퇴 (논리적 삭제) ---
    @Operation(summary = "회원 탈퇴 (논리적 삭제)", description = "memberId에 해당하는 회원을 논리적으로 삭제합니다.",
               responses = {
                       @ApiResponse(responseCode = "204", description = "회원 탈퇴 성공 (내용 없음)"),
                       @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
               })
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> withdrawMember(
            @Parameter(description = "탈퇴할 회원의 memberId", required = true, example = "1")
            @PathVariable Long memberId) {
        if (memberService.findMemberById(memberId).isPresent()) {
            memberService.withdrawMember(memberId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}