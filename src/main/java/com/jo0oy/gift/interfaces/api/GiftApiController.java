package com.jo0oy.gift.interfaces.api;

import com.jo0oy.gift.application.GiftFacade;
import com.jo0oy.gift.common.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifts")
@RestController
public class GiftApiController {

    private final GiftFacade giftFacade;
    private final GiftDtoMapper giftDtoMapper;

    @PostMapping
    public ResponseEntity<?> registerOrder(@RequestBody @Valid GiftDto.RegisterReq request) {
        var data = giftFacade.registerOrder(giftDtoMapper.toCommand(request));

        return ResponseEntity.created(URI.create("/api/v1/gifts"))
                .body(ResultResponse.success("선물 주문 등록 성공", new GiftDto.RegisterResponse(data)));
    }

    @GetMapping("/{giftToken}")
    public ResponseEntity<?> getGiftInfo(@PathVariable("giftToken") String giftToken) {
        var data = giftFacade.getGiftInfo(giftToken);

        return ResponseEntity.ok(ResultResponse.success("선물 주문 정보 조회 성공", giftDtoMapper.toDto(data)));
    }

    @PostMapping("/{giftToken}/payment-processing")
    public ResponseEntity<?> requestPaymentProcessing(@PathVariable String giftToken) {
        giftFacade.requestPaymentProcessing(giftToken);
        return ResponseEntity.ok(ResultResponse.success("결제 요청 성공"));
    }

    @PostMapping("/{giftToken}/accept-gift")
    public ResponseEntity<?> acceptGift(
            @PathVariable String giftToken,
            @RequestBody @Valid GiftDto.AcceptReq request
    ) {
        var acceptCommand = giftDtoMapper.toCommand(giftToken, request);
        log.info("giftCommand.Accept={}", acceptCommand);
        giftFacade.acceptGift(acceptCommand);
        return ResponseEntity.ok(ResultResponse.success("선물 수락 성공"));
    }

}
