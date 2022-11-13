package com.jpachalle.jpachallenge.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpachalle.jpachallenge.enums.MultiFactorAuthenticationType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;

@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
@Table(name = "j_user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED COMMENT '사용자 PK'")
    private BigInteger id;

    @CreationTimestamp
    @Column(name = "created_datetime", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP(6) DEFAULT NOW(6) COMMENT '생성일'")
    private Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "updated_datetime", nullable = false,
            columnDefinition = "TIMESTAMP(6) DEFAULT NOW(6) COMMENT '수정일'")
    private Timestamp updatedDateTime;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false,
            columnDefinition = "BINARY(144) COMMENT '사용자 비밀번호, SHA-512 PBKDF2 알고리즘 180000회 Iter'")
    private byte[] password;

    @Column(name = "email_address", nullable = false, unique = true,
            columnDefinition = "VARCHAR(254) COMMENT '사용자 이메일 주소, 아이디로 사용된다.'")
    private String emailAddress;

    @ColumnDefault("false")
    @Column(name = "email_address_verified", nullable = false,
            columnDefinition = "BIT(1) COMMENT '사용자 이메일 인증여부'")
    private Boolean emailAddressVerified;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "salt", nullable = false, columnDefinition = "BINARY(64) COMMENT '비밀번호 SALT 값'")
    private byte[] salt;

    @Column(name = "mobile_number", unique = true,
            columnDefinition = "VARCHAR(11) COMMENT '휴대폰 번호, 01012341234, 0101231234'")
    private String mobileNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_card_id",
            columnDefinition = "BIGINT UNSIGNED COMMENT '사용자 등록 카드 FK'")
    private RegisteredCard registeredCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false,
            columnDefinition = "BIGINT UNSIGNED COMMENT '역할 FK'")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id", nullable = false,
            columnDefinition = "BIGINT UNSIGNED COMMENT '등급 FK'")
    private Grade grade;

    @Column(name = "direct_inward_dial_number", columnDefinition = "VARCHAR(11)")
    private String directInwardDialNumber;

    @Column(name = "name", columnDefinition = "VARCHAR(16) COMMENT '사용자 명'")
    private String name;

    @ColumnDefault("'PHONE'")
    @Column(name = "e_multi_factor_authentication_type", nullable = false,
            columnDefinition = "VARCHAR(8) COMMENT '2차 인증 수단, MultiFactorAuthenticationType'")
    @Enumerated(EnumType.STRING)
    private MultiFactorAuthenticationType multiFactorAuthenticationType;

    @Column(name = "email_agreed_datetime", columnDefinition = "TIMESTAMP(6) COMMENT '사용자 이메일 동의 여부'")
    private Timestamp emailAgreedDatetime;

    @Column(name = "sms_agreed_datetime", columnDefinition = "TIMESTAMP(6) COMMENT '사용자 sms 동의 여부'")
    private Timestamp smsAgreedDatetime;

    @Builder
    public User(String emailAddress, Boolean emailAddressVerified, byte[] password, byte[] salt,
                String mobileNumber, RegisteredCard registeredCard, Role role, Grade grade,
                String directInwardDialNumber, String name,
                MultiFactorAuthenticationType multiFactorAuthenticationType, Timestamp emailAgreedDatetime,
                Timestamp smsAgreedDatetime) {
        this.emailAddress = emailAddress;
        this.emailAddressVerified = emailAddressVerified;
        this.password = password;
        this.salt = salt;
        this.mobileNumber = mobileNumber;
        this.registeredCard = registeredCard;
        this.role = role;
        this.grade = grade;
        this.directInwardDialNumber = directInwardDialNumber;
        this.name = name;
        this.multiFactorAuthenticationType = multiFactorAuthenticationType;
        this.emailAgreedDatetime = emailAgreedDatetime;
        this.smsAgreedDatetime = smsAgreedDatetime;
    }

}
