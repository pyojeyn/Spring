package com.jpachalle.jpachallenge.entity;

import com.jpachalle.jpachallenge.enums.GradeName;
import lombok.AccessLevel;
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
@Table(name = "j_grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED COMMENT '사용자 등급 PK'")
    private BigInteger id;

    @Column(name = "e_name", nullable = false, unique = true,
            columnDefinition = "VARCHAR(32) COMMENT '회원 등급 명, 브론즈, 골드 등등..'")
    @Enumerated(EnumType.STRING)
    private GradeName Name;
}
