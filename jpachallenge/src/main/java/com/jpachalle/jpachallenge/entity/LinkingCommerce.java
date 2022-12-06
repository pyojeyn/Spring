package com.jpachalle.jpachallenge.entity;

import com.jpachalle.jpachallenge.enums.CommerceName;
import com.jpachalle.jpachallenge.enums.CountryCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "integration__linking_commerce")
public class LinkingCommerce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED COMMENT '연동 커머스 FK'")
    private BigInteger id;

    @Column(name = "e_name", nullable = false, unique = true,
            columnDefinition = "VARCHAR(32) COMMENT '연동 커머스 이름, CommerceName'")
    @Enumerated(EnumType.STRING)
    private CommerceName name;

    @Column(name = "e_country_code", nullable = false,
            columnDefinition = "VARCHAR(2) COMMENT '연동 커머스 국가 코드'")
    @Enumerated(EnumType.STRING)
    private CountryCode countrycode;

    @Builder
    public LinkingCommerce(CommerceName name) {
        this.name = name;
    }
}
