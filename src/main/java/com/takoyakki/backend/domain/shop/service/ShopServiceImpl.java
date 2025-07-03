package com.takoyakki.backend.domain.shop.service;

import com.takoyakki.backend.domain.shop.dto.request.AddToBagRequestDto;
import com.takoyakki.backend.domain.shop.dto.request.PurchaseRequestDto;
import com.takoyakki.backend.domain.shop.dto.response.BagItemResponseDto;
import com.takoyakki.backend.domain.shop.dto.response.StickerResponseDto;
import com.takoyakki.backend.domain.shop.repository.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopMapper shopMapper;

    @Override
    public List<StickerResponseDto> getAllStickersWithPurchaseStatus(Long memberId) {
        List<Long> ownedStickerIds = shopMapper.getPurchasedStickerIds(memberId);
        return shopMapper.getAllStickers().stream()
                .map(sticker -> StickerResponseDto.builder()
                        .id(sticker.getId())
                        .name(sticker.getName())
                        .imageUrl(sticker.getImageUrl())
                        .cost(sticker.getCost())
                        .purchased(ownedStickerIds.contains(sticker.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean purchaseSticker(PurchaseRequestDto dto) {
        Long memberId = dto.getMemberId();
        Long stickerId = dto.getStickerId();

        // 중복 구매 방지
        if (shopMapper.hasSticker(memberId, stickerId)) return false;

        // 포인트 부족 체크
        int userPoint = shopMapper.getUserPoint(memberId);
        int cost = shopMapper.getStickerCost(stickerId);
        if (userPoint < cost) return false;

        // 구매 처리
        shopMapper.insertUserSticker(memberId, stickerId);
        shopMapper.deductUserPoint(memberId, cost);
        return true;
    }

    @Override
    public List<StickerResponseDto> getUserInventory(Long memberId) {
        return shopMapper.getUserInventory(memberId).stream()
                .map(s -> StickerResponseDto.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .imageUrl(s.getImageUrl())
                        .cost(s.getCost())
                        .purchased(true)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<BagItemResponseDto> getBagItems(Long memberId) {
        return shopMapper.getBagItems(memberId).stream()
                .map(slot -> BagItemResponseDto.builder()
                        .stickerId(slot.getStickerId())
                        .name(slot.getName())
                        .imageUrl(slot.getImageUrl())
                        .slotIndex(slot.getSlotIndex())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean addStickerToBag(AddToBagRequestDto dto) {
        // 중복 체크
        boolean exists = shopMapper.bagSlotExists(dto.getMemberId(), dto.getStickerId());
        if (exists) return false;

        // 슬롯 수 제한: 구현 시 DB 쿼리에서 10개 넘는지 검사해도 됨
        shopMapper.insertBagSlot(dto.getMemberId(), dto.getStickerId(), dto.getSlotIndex());
        return true;
    }

    @Override
    public void removeFromBag(Long memberId, int slotIndex) {
        shopMapper.removeBagSlot(memberId, slotIndex);
    }
}
