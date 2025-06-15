package com.takoyakki.backend.domain.member.service;

import com.takoyakki.backend.domain.member.model.Member;
import com.takoyakki.backend.domain.member.dto.MemberRequestDto; // DTO 임포트

import java.util.Optional; // Optional 임포트

public interface MemberService {
    // 회원 가입
    Member registerMember(MemberRequestDto requestDto);
    // ID로 회원 조회
    Optional<Member> findMemberById(Long memberId);
    // 로그인 ID (string id)로 회원 조회
    Optional<Member> findMemberByLoginId(String loginId);
    // 이메일로 회원 조회
    Optional<Member> findMemberByEmail(String email);
    // 닉네임으로 회원 조회
    Optional<Member> findMemberByNickname(String nickname);
    // 회원 정보 업데이트
    void updateMember(Long memberId, MemberRequestDto requestDto);
    // 회원 탈퇴 (논리적 삭제)
    void withdrawMember(Long memberId);
    // 비밀번호 확인 (간단 예시, 실제로는 더 복잡한 로직 필요)
    boolean checkPassword(String loginId, String password);
}