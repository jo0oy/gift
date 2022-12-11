package com.jo0oy.gift.infrastructures.gift.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class RetrofitOrderApiResponse {

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        private String orderToken;
    }

}
