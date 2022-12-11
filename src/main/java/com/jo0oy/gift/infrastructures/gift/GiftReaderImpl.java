package com.jo0oy.gift.infrastructures.gift;

import com.jo0oy.gift.common.exception.EntityNotFoundException;
import com.jo0oy.gift.domain.gift.Gift;
import com.jo0oy.gift.domain.gift.GiftReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class GiftReaderImpl implements GiftReader {

    private final GiftRepository giftRepository;

    @Override
    public Gift getGiftBy(String giftToken) {

        log.info("GiftReader 선물 조회 By giftToken 실행");
        return giftRepository.findByGiftToken(giftToken)
                .orElseThrow(() -> {
                    log.error("존재하지 않는 gift 엔티티입니다. giftToken={}", giftToken);
                    throw new EntityNotFoundException("존재하지 않는 gift 엔티티입니다. giftToken=" + giftToken);
                });
    }

    @Override
    public Gift getGiftByOrderToken(String orderToken) {

        log.info("GiftReader 선물 조회 By orderToken 실행");
        return giftRepository.findByOrderToken(orderToken)
                .orElseThrow(() -> {
                    log.error("존재하지 않는 gift 엔티티입니다. orderToken={}", orderToken);
                    throw new EntityNotFoundException("존재하지 않는 gift 엔티티입니다. orderToken=" + orderToken);
                });
    }
}
