package com.takoyakki.backend.domain.member.repository;

import com.takoyakki.backend.domain.member.model.Member; // ★ Member 모델 임포트
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member findByMemberId(@Param("memberId") Long memberId);
    Member findById(@Param("id") String id); // 로그인 ID로 회원 조회
    Member findByEmail(@Param("email") String email);
    Member findByNickname(@Param("nickname") String nickname);
    void insert(Member member);
    void update(Member member);
    void softDelete(@Param("memberId") Long memberId); // 논리적 삭제
    // 기타 필요 메서드 (비밀번호 업데이트, 역할 변경 등)
}
