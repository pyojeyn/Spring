package com.jpachalle.jpachallenge.entity;

import com.jpachalle.jpachallenge.enums.ProductCountryCode;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "j_user_linking_commerce",
        indexes = @Index(name = "fk_sum", columnList = "user_id, linking_commerce_id", unique = true))
public class UserLinkingCommerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED COMMENT '사용자 linking commerce PK'")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            columnDefinition = "BIGINT UNSIGNED COMMENT '사용자 FK'")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linking_commerce_id", nullable = false,
            columnDefinition = "BIGINT UNSIGNED COMMENT 'linking commerce FK'")
    private LinkingCommerce linkingCommerce;

    @Column(name = "token", nullable = false,
            columnDefinition = "TEXT COMMENT 'API 토큰길이, 2251까지 길이를 본적있다.'")
    private String token;

    @Column(name = "alias_name", nullable = false, columnDefinition = "VARCHAR(64)")
    private String aliasName;

    @Column(name = "commerce_username", nullable = false, columnDefinition = "VARCHAR(255)")
    private String commerceUsername;

    @Column(name = "commerce_password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String commercePassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "e_product_country_code", nullable = false,
            columnDefinition = "VARCHAR(2) COMMENT '상품위치에 대한 국가코드'")
    private ProductCountryCode productCountryCode;

    @Column(name = "product_zip_code", nullable = false,
            columnDefinition = "MEDIUMINT UNSIGNED COMMENT '상품위치 우편번호'")
    private Long productZipCode;

    @Column(name = "product_city_or_state", nullable = false,
            columnDefinition = "VARCHAR(32) COMMENT '상품위치 주/도'")
    private String productCityOrState;

    @Setter
    @Column(name = "is_order_automation_active", nullable = false,
            columnDefinition = "BIT(1) COMMENT '설정 > 작업을 진행할 쇼핑몰 선택이 되면 해당됨'")
    private Boolean isOrderAutomationActive;

    @Builder
    public UserLinkingCommerce(User user, LinkingCommerce linkingCommerce, String token,
                               String aliasName, String commerceUsername, String commercePassword,
                               ProductCountryCode productCountryCode, Long productZipCode, String productCityOrState,
                               Boolean isOrderAutomationActive) {
        this.user = user;
        this.linkingCommerce = linkingCommerce;
        this.token = token;
        this.aliasName = aliasName;
        this.commerceUsername = commerceUsername;
        this.commercePassword = commercePassword;
        this.productCountryCode = productCountryCode;
        this.productZipCode = productZipCode;
        this.productCityOrState = productCityOrState;
        this.isOrderAutomationActive = isOrderAutomationActive;
    }
}
