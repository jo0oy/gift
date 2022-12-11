package com.jo0oy.gift.interfaces.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class GiftPayCompleteMessage {
    private String orderToken;
}
