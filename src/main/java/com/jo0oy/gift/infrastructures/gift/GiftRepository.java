package com.jo0oy.gift.infrastructures.gift;

import com.jo0oy.gift.domain.gift.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Long> {

    Optional<Gift> findByGiftToken(String giftToken);

    Optional<Gift> findByOrderToken(String orderToken);
}
