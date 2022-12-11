package com.jo0oy.gift.domain.gift.order;

import com.jo0oy.gift.domain.gift.GiftCommand;

public interface OrderApiCaller {
    String registerGiftOrder(OrderApiCommand.RegisterOrder command);

    void updateReceiverInfo(String orderToken, GiftCommand.Accept command);
}
