package com.takoyakki.backend.domain.member.service;

import com.takoyakki.backend.domain.member.model.Member;
import com.takoyakki.backend.domain.member.dto.MemberRequestDto;
import com.takoyakki.backend.domain.member.repository.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.security.crypto.password.PasswordEncoder; // 비밀번호 암호화 시 필요

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    // private final PasswordEncoder passwordEncoder; // 비밀번호 암호화 시 주입

    public MemberServiceImpl(MemberMapper memberMapper /*, PasswordEncoder passwordEncoder */) {
        this.memberMapper = memberMapper;
        // this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member registerMember(MemberRequestDto requestDto) {
        // 중복 검사 (ID, 이메일, 닉네임)
        if (memberMapper.findById(requestDto.getId()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 ID입니다.");
        }
        if (requestDto.getEmail() != null && memberMapper.findByEmail(requestDto.getEmail()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        if (requestDto.getNickname() != null && memberMapper.findByNickname(requestDto.getNickname()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        Member member = new Member();
        member.setId(requestDto.getId());
        // 비밀번호 암호화 (실제 프로젝트에서는 필수)
        // member.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        member.setPassword(requestDto.getPassword()); // 임시로 평문 저장
        member.setMemberName(requestDto.getMemberName());
        member.setEmail(requestDto.getEmail());
        member.setPhone(requestDto.getPhone());
        member.setNickname(requestDto.getNickname());
        member.setRole("USER"); // 기본 역할
        member.setStatus("ACTIVE"); // 기본 상태
        member.setTotalXp(0);
        member.setUsablePoints(0);
        member.setCurrentLevel(1);
        member.setCreatedAt(LocalDateTime.now());
        member.setDeleted(false);

        memberMapper.insert(member);
        return member;
    }

    @Override
    public Optional<Member> findMemberById(Long memberId) {
        return Optional.ofNullable(memberMapper.findByMemberId(memberId));
    }

    @Override
    public Optional<Member> findMemberByLoginId(String loginId) {
        return Optional.ofNullable(memberMapper.findById(loginId));
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return Optional.ofNullable(memberMapper.findByEmail(email));
    }

    @Override
    public Optional<Member> findMemberByNickname(String nickname) {
        return Optional.ofNullable(memberMapper.findByNickname(nickname));
    }

    @Override
    public void updateMember(Long memberId, MemberRequestDto requestDto) {
        Member existingMember = memberMapper.findByMemberId(memberId);
        if (existingMember == null || existingMember.isDeleted()) {
            throw new IllegalArgumentException("회원을 찾을 수 없거나 삭제된 회원입니다.");
        }

        // DTO의 값으로 업데이트 (null이 아닌 경우만)
        if (requestDto.getPassword() != null && !requestDto.getPassword().isEmpty()) {
            // existingMember.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            existingMember.setPassword(requestDto.getPassword());
        }
        if (requestDto.getMemberName() != null) existingMember.setMemberName(requestDto.getMemberName());
        // 이메일, 닉네임은 중복 검사 후 업데이트 필요 (예시 생략, 실제로는 추가)
        if (requestDto.getEmail() != null) existingMember.setEmail(requestDto.getEmail());
        if (requestDto.getPhone() != null) existingMember.setPhone(requestDto.getPhone());
        if (requestDto.getNickname() != null) existingMember.setNickname(requestDto.getNickname());

        existingMember.setUpdatedAt(LocalDateTime.now());
        memberMapper.update(existingMember);
    }

    @Override
    public void withdrawMember(Long memberId) {
        memberMapper.softDelete(memberId);
    }

    @Override
    public boolean checkPassword(String loginId, String password) {
        return findMemberByLoginId(loginId)
                .map(member -> {
                    // return passwordEncoder.matches(password, member.getPassword());
                    return password.equals(member.getPassword()); // 임시 평문 비교
                })
                .orElse(false);
    }
}