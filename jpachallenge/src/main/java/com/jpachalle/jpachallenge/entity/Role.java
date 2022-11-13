package com.jpachalle.jpachallenge.entity;

import com.jpachalle.jpachallenge.enums.RoleName;
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
@Table(name = "j_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED COMMENT '역할 PK'")
    private BigInteger id;

    @Column(name = "e_name", nullable = false, unique = true,
            columnDefinition = "VARCHAR(32) COMMENT '역할 명, RoleName, 총관리자, 일반관리자 등'")
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
