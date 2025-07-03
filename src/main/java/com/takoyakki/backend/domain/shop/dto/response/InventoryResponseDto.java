package com.takoyakki.backend.domain.shop.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InventoryResponseDto {
    private Long memberId;
    private String nickname;  // 사용자 닉네임 (옵션)
    private List<StickerResponseDto> stickers;
}
