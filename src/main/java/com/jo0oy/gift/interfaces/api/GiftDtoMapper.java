package com.jo0oy.gift.interfaces.api;

import com.jo0oy.gift.domain.gift.GiftCommand;
import com.jo0oy.gift.domain.gift.GiftInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftDtoMapper {

    // DTO -> Command
    GiftCommand.RegisterGift toCommand(GiftDto.RegisterReq request);

    GiftCommand.RegisterOrderItem toCommand(GiftDto.RegisterOrderItemReq request);

    GiftCommand.RegisterOrderItemOptionGroup toCommand(GiftDto.RegisterOrderItemOptionGroupReq request);

    GiftCommand.RegisterOrderItemOption toCommand(GiftDto.RegisterOrderItemOptionReq request);

    GiftCommand.Accept toCommand(String giftToken, GiftDto.AcceptReq request);

    // Info -> DTO
    GiftDto.GiftInfoResponse toDto(GiftInfo.MainInfo info);
}
