package com.jpachalle.jpachallenge.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "j_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private BigInteger id;

    @Column(name = "code", nullable = false, unique = true, columnDefinition = "VARCHAR(12)")
    private String code;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(64)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_group_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private CategoryGroup categoryGroup;

    @Builder
    public Category(String code, String name, CategoryGroup categoryGroup) {
        this.code = code;
        this.name = name;
        this.categoryGroup = categoryGroup;
    }
}
