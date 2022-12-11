package com.jo0oy.gift.infrastructures.gift.order;

import com.jo0oy.gift.common.response.ResultResponse;
import com.jo0oy.gift.domain.gift.GiftCommand;
import com.jo0oy.gift.domain.gift.order.OrderApiCaller;
import com.jo0oy.gift.domain.gift.order.OrderApiCommand;
import com.jo0oy.gift.infrastructures.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderApiCallerImpl implements OrderApiCaller {

    private final RetrofitOrderApi retrofitOrderApi;
    private final RetrofitUtils retrofitUtils;

    @Override
    public String registerGiftOrder(OrderApiCommand.RegisterOrder command) {
        var call = retrofitOrderApi.registerOrder(command);
        return retrofitUtils.responseSync(call)
                .map(ResultResponse::getData)
                .map(RetrofitOrderApiResponse.RegisterResponse::getOrderToken)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateReceiverInfo(String orderToken, GiftCommand.Accept command) {
        var call = retrofitOrderApi.updateReceiverInfo(orderToken, command);
        retrofitUtils.responseVoid(call);
    }
}
