package com.takoyakki.backend.domain.shop.service;

import com.takoyakki.backend.domain.shop.dto.request.AddToBagRequestDto;
import com.takoyakki.backend.domain.shop.dto.request.PurchaseRequestDto;
import com.takoyakki.backend.domain.shop.dto.response.BagItemResponseDto;
import com.takoyakki.backend.domain.shop.dto.response.StickerResponseDto;

import java.util.List;

public interface ShopService {

    List<StickerResponseDto> getAllStickersWithPurchaseStatus(Long memberId);

    boolean purchaseSticker(PurchaseRequestDto dto);

    List<StickerResponseDto> getUserInventory(Long memberId);

    List<BagItemResponseDto> getBagItems(Long memberId);

    boolean addStickerToBag(AddToBagRequestDto dto);

    void removeFromBag(Long memberId, int slotIndex);
}
