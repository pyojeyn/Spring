package com.jpachalle.jpachallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListItemResponseDto<T> {

    @NotNull
    private Integer limit;

    @NotNull
    private Long offset;

    @NotNull
    private Long total;

    @NotNull
    private Integer count;

    @NotNull
    private List<T> items;
}
