package com.jo0oy.gift.domain.gift;

import com.jo0oy.gift.domain.gift.order.OrderApiCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftToOrderMapper {

    OrderApiCommand.RegisterOrder toOrderCommand(GiftCommand.RegisterGift command);

    OrderApiCommand.RegisterOrderItem toOrderCommand(GiftCommand.RegisterOrderItem command);

    OrderApiCommand.RegisterOrderItemOptionGroup toOrderCommand(GiftCommand.RegisterOrderItemOptionGroup command);

    OrderApiCommand.RegisterOrderItemOption toOrderCommand(GiftCommand.RegisterOrderItemOption command);
}
