package com.jpachalle.jpachallenge.service;

import com.jpachalle.jpachallenge.dto.CreateResponseDto;
import com.jpachalle.jpachallenge.dto.SuccessResponseDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupUpdateRequestDto;

import java.math.BigInteger;

public interface CatalogService {
    public CreateResponseDto<BigInteger> createCategoryGroup(CategoryGroupCreateRequestDto body);

    public SuccessResponseDto updateCategoryGroup(CategoryGroupUpdateRequestDto body, BigInteger categoryGroupId);

    CreateResponseDto<BigInteger> createCategory(CategoryCreateRequestDto body);
}
