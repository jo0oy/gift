package com.jo0oy.gift.domain.gift;

public interface GiftService {

    GiftInfo.MainInfo registerOrder(GiftCommand.RegisterGift command);

    GiftInfo.MainInfo getGiftInfo(String giftToken);

    void requestPaymentProcessing(String giftToken);

    void completePayment(String orderToken);

    void acceptGift(GiftCommand.Accept command);
}
