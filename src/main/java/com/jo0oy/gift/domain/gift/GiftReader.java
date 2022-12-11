package com.jo0oy.gift.domain.gift;

public interface GiftReader {
    Gift getGiftBy(String giftToken);

    Gift getGiftByOrderToken(String orderToken);
}
