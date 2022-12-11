package com.jo0oy.gift.domain.gift;

import com.jo0oy.gift.domain.gift.delivery.Delivery;
import com.jo0oy.gift.domain.gift.order.OrderApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftServiceImpl implements GiftService {

    private final GiftStore giftStore;
    private final GiftReader giftReader;
    private final OrderApiCaller orderApiCaller;
    private final GiftToOrderMapper giftToOrderMapper;

    @Override
    @Transactional
    public GiftInfo.MainInfo registerOrder(GiftCommand.RegisterGift command) {
        log.info("GiftService 선물 등록 로직 실행");
        var orderCommand = giftToOrderMapper.toOrderCommand(command);
        var orderToken = orderApiCaller.registerGiftOrder(orderCommand);
        var storedGift = giftStore.store(command.toEntity(orderToken));
        return new GiftInfo.MainInfo(storedGift);
    }

    @Override
    @Transactional(readOnly = true)
    public GiftInfo.MainInfo getGiftInfo(String giftToken) {
        var gift = giftReader.getGiftBy(giftToken);
        return new GiftInfo.MainInfo(gift);
    }

    @Override
    @Transactional
    public void requestPaymentProcessing(String giftToken) {
        var gift = giftReader.getGiftBy(giftToken);
        gift.inPayment();
    }

    @Override
    @Transactional
    public void completePayment(String orderToken) {
        var gift = giftReader.getGiftByOrderToken(orderToken);
        gift.completePayment();
    }

    @Override
    @Transactional
    public void acceptGift(GiftCommand.Accept command) {
        var giftToken = command.getGiftToken();
        var deliveryInfo = Delivery.builder()
                .receiverName(command.getReceiverName())
                .receiverPhone(command.getReceiverPhone())
                .receiverZipcode(command.getReceiverZipcode())
                .receiverAddress1(command.getReceiverAddress1())
                .receiverAddress2(command.getReceiverAddress2())
                .etcMessage(command.getEtcMessage())
                .build();

        var gift = giftReader.getGiftBy(giftToken);
        gift.accept(deliveryInfo);

        orderApiCaller.updateReceiverInfo(gift.getOrderToken(), command);
    }
}
