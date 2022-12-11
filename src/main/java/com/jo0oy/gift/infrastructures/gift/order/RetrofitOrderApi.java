package com.jo0oy.gift.infrastructures.gift.order;

import com.jo0oy.gift.common.response.ResultResponse;
import com.jo0oy.gift.domain.gift.GiftCommand;
import com.jo0oy.gift.domain.gift.order.OrderApiCommand;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitOrderApi {

    @POST("api/v1/gift-orders/init")
    Call<ResultResponse<RetrofitOrderApiResponse.RegisterResponse>> registerOrder(@Body OrderApiCommand.RegisterOrder request);

    @POST("api/v1/gift-orders/{orderToken}/update-receiver-info")
    Call<Void> updateReceiverInfo(@Path("orderToken") String orderToken, @Body GiftCommand.Accept request);
}
