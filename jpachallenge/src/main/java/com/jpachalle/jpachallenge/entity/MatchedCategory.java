package com.jpachalle.jpachallenge.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "j_matched_category", indexes = @Index(name = "uniq_sum",
        columnList = "user_linking_commerce_id, linking_commerce_category_id", unique = true))
public class MatchedCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_linking_commerce_id", nullable = false,
            columnDefinition = "BIGINT UNSIGNED COMMENT '사용자 연결된 커머스 FK, 같은 카테고리 안에 여러개 들어올 수 있다.'")
    private UserLinkingCommerce userLinkingCommerce;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false,
            columnDefinition = "BIGINT UNSIGNED COMMENT '카테고리 FK'")
    private Category category;

    @Column(name = "linking_commerce_category_id", nullable = false,
            columnDefinition = "VARCHAR(32) COMMENT '연결된 커머스 내에서 사용하는 카테고리 ID'")
    private String linkingCommerceCategoryId;

    @Column(name = "linking_commerce_category_name", nullable = false,
            columnDefinition = "VARCHAR(255) COMMENT '연결된 커머스 내에서 사용하는 카테고리 이름'")
    private String linkingCommerceCategoryName;

    @Builder
    public MatchedCategory(UserLinkingCommerce userLinkingCommerce, Category category,
                           String linkingCommerceCategoryId, String linkingCommerceCategoryName) {
        this.userLinkingCommerce = userLinkingCommerce;
        this.category = category;
        this.linkingCommerceCategoryId = linkingCommerceCategoryId;
        this.linkingCommerceCategoryName = linkingCommerceCategoryName;
    }
}
