package com.jo0oy.gift.infrastructures.gift;

import com.jo0oy.gift.domain.gift.Gift;
import com.jo0oy.gift.domain.gift.GiftStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class GiftStoreImpl implements GiftStore {

    private final GiftRepository giftRepository;

    @Override
    public Gift store(Gift gift) {
        log.info("GiftStore 선물 등록 저장 실행");
        return giftRepository.save(gift);
    }
}
