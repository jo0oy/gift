package com.jo0oy.gift.domain.gift;

import com.jo0oy.gift.common.exception.IllegalStatusException;
import com.jo0oy.gift.common.exception.InvalidParamException;
import com.jo0oy.gift.common.util.TokenGenerator;
import com.jo0oy.gift.domain.BaseTimeEntity;
import com.jo0oy.gift.domain.gift.delivery.Delivery;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gifts")
@Entity
public class Gift extends BaseTimeEntity {

    private static final String GIFT_PREFIX = "gft_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String giftToken;

    private Long buyerUserId;

    private String orderToken;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PushType pushType;

    private String giftReceiverName;

    private String giftReceiverPhone;

    private String giftMessage;

    @Embedded
    private Delivery deliveryInfo;

    private ZonedDateTime paidAt;

    private ZonedDateTime pushedAt;

    private ZonedDateTime acceptedAt;

    private ZonedDateTime expiredAt;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("선물 주문 생성"),
        IN_PAYMENT("결제 중"),
        ORDER_COMPLETE("주문 완료"),
        PUSH_COMPLETE("선물 링크 발송 완료"),
        ACCEPT("선물 수락"),
        DELIVERY_PREPARE("상품준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료"),
        EXPIRATION("선물 수락 만료");

        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum PushType {
        KAKAO("카카오톡"),
        LMS("문자");

        private final String description;
    }

    @Builder
    public Gift(Long buyerUserId,
                String orderToken,
                PushType pushType,
                String giftReceiverName,
                String giftReceiverPhone,
                String giftMessage) {

        if (Objects.isNull(buyerUserId)) throw new InvalidParamException("Gift constructor buyerUserId is null");
        if (Objects.isNull(pushType)) throw new InvalidParamException("Gift constructor pushType is null");
        if (StringUtils.isEmpty(giftReceiverName)) throw new InvalidParamException("Gift constructor giftReceiverName is empty");
        if (StringUtils.isEmpty(giftReceiverPhone)) throw new InvalidParamException("Gift constructor giftReceiverPhone is empty");
        if (StringUtils.isEmpty(giftMessage)) throw new InvalidParamException("Gift constructor giftMessage is empty");


        this.giftToken = TokenGenerator.randomCharacterWithPrefix(GIFT_PREFIX);
        this.buyerUserId = buyerUserId;
        this.orderToken = orderToken;
        this.status = Status.INIT;
        this.pushType = pushType;
        this.giftReceiverName = giftReceiverName;
        this.giftReceiverPhone = giftReceiverPhone;
        this.giftMessage = giftMessage;
        this.expiredAt = ZonedDateTime.now().plusDays(7);
    }

    public void inPayment() {
        if (this.status != Status.INIT) throw new IllegalStatusException("Gift inPayment");
        this.status = Status.IN_PAYMENT;
    }

    public void completePayment() {
        if (this.status != Status.IN_PAYMENT) throw new IllegalStatusException("Gift paymentComplete");
        this.status = Status.ORDER_COMPLETE;
        this.paidAt = ZonedDateTime.now();
    }

    public void pushLink() {
        if (this.status != Status.ORDER_COMPLETE) throw new IllegalStatusException("Gift pushLink");
        this.status = Status.PUSH_COMPLETE;
        this.pushedAt = ZonedDateTime.now();
    }

    public void accept(Delivery deliveryInfo) {
        var receiverName = deliveryInfo.getReceiverName();
        var receiverPhone = deliveryInfo.getReceiverPhone();
        var receiverZipcode = deliveryInfo.getReceiverZipcode();
        var receiverAddress1 = deliveryInfo.getReceiverAddress1();
        var receiverAddress2 = deliveryInfo.getReceiverAddress2();
        var etcMessage = deliveryInfo.getEtcMessage();

        if (!availableAccept()) throw new IllegalStatusException();
        if (StringUtils.isEmpty(receiverName)) throw new InvalidParamException("Gift accept receiverName is empty");
        if (StringUtils.isEmpty(receiverPhone)) throw new InvalidParamException("Gift accept receiverPhone is empty");
        if (StringUtils.isEmpty(receiverZipcode)) throw new InvalidParamException("Gift accept receiverZipcode is empty");
        if (StringUtils.isEmpty(receiverAddress1)) throw new InvalidParamException("Gift accept receiverAddress1 is empty");
        if (StringUtils.isEmpty(receiverAddress2)) throw new InvalidParamException("Gift accept receiverAddress2 is empty");
        if (StringUtils.isEmpty(etcMessage)) throw new InvalidParamException("Gift accept etcMessage is empty");

        this.status = Status.ACCEPT;
        this.deliveryInfo = deliveryInfo;
        this.acceptedAt = ZonedDateTime.now();
    }

    public void expired() {
        this.status = Status.EXPIRATION;
        this.expiredAt = ZonedDateTime.now();
    }

    private boolean availableAccept() {
        if (this.expiredAt.isBefore(ZonedDateTime.now())) return false;
        return this.status == Status.ORDER_COMPLETE || this.status == Status.PUSH_COMPLETE;
    }
}
