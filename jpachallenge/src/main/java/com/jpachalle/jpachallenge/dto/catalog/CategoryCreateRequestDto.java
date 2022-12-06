package com.jpachalle.jpachallenge.dto.catalog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
public class CategoryCreateRequestDto {
    @NotBlank
    @Length(min = 1, max = 50)
    private String name;

    @NotNull
    private BigInteger categoryGroupId;

    private List<MatchedCategoryWrapper> matchedCategories;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class MatchedCategoryWrapper {

        private BigInteger userLinkingCommerceId; // 사용자와 연결된 커머스 PK

        @Length(min = 1, max = 32)
        private String linkingCommerceCategoryId; // 연결된 커머스의 최종 카테고리 ID

        @Length(min = 1, max = 255)
        private List<String> linkingCommerceCategoryName;
    }
    // MatchedCategory 테이블이 Category 의 외래키를 가지고 있음. Category 한테 ManyToOne 걸어놈

}
