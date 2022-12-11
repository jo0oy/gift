package com.jo0oy.gift.application;

import com.jo0oy.gift.domain.gift.GiftCommand;
import com.jo0oy.gift.domain.gift.GiftInfo;
import com.jo0oy.gift.domain.gift.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftFacade {

    private final GiftService giftService;

    //선물 주문 등록
    public GiftInfo.MainInfo registerOrder(GiftCommand.RegisterGift command) {
        log.info("GiftFacade registerOrder 실행");
        return giftService.registerOrder(command);
    }

    //선물 조회
    public GiftInfo.MainInfo getGiftInfo(String giftToken) {
        log.info("GiftFacade getGiftInfo 실행, giftToken={}", giftToken);
        return giftService.getGiftInfo(giftToken);
    }

    // 결제
    public void requestPaymentProcessing(String giftToken) {
        giftService.requestPaymentProcessing(giftToken);
    }

    // 결제 완료 처리
    public void completePayment(String orderToken) {
        giftService.completePayment(orderToken);
    }

    // 선물 수락
    public void acceptGift(GiftCommand.Accept request) {
        giftService.acceptGift(request);
    }
}
