package com.jo0oy.gift.domain.gift;

import lombok.Getter;
import lombok.ToString;

public class GiftInfo {

    @Getter
    @ToString
    public static class MainInfo {
        private final String orderToken;
        private final String giftToken;
        private final Gift.PushType pushType;
        private final String giftReceiverName;
        private final String giftReceiverPhone;
        private final String giftMessage;

        public MainInfo(Gift gift) {
            this.orderToken = gift.getOrderToken();
            this.giftToken = gift.getGiftToken();
            this.pushType = gift.getPushType();
            this.giftReceiverName = gift.getGiftReceiverName();
            this.giftReceiverPhone = gift.getGiftReceiverName();
            this.giftMessage = gift.getGiftMessage();
        }

    }
}
