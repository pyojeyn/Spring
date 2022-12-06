package com.jpachalle.jpachallenge.controller;

import com.jpachalle.jpachallenge.dto.CreateResponseDto;
import com.jpachalle.jpachallenge.dto.SuccessResponseDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupUpdateRequestDto;
import com.jpachalle.jpachallenge.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/catalog")
@RestController
public class CatalogController {

    private final CatalogService catalogService;

    /**
     * 카테고리 그룹 생성
     * @param body
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/category-groups")
    public CreateResponseDto<BigInteger> createCategoryGroup(
            @Valid @RequestBody CategoryGroupCreateRequestDto body){
        return catalogService.createCategoryGroup(body);
    }

    /**
     * 카테고리 그룹 수정
     * @param body
     * @param categoryGroupId
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/category-groups/{categoryGroupId}")
    public SuccessResponseDto updateCategoryGroup(
            @Valid @RequestBody CategoryGroupUpdateRequestDto body,
            @PathVariable BigInteger categoryGroupId){
        return catalogService.updateCategoryGroup(body, categoryGroupId);
    }

    /**
     * 카테고리 생성
     * @param body
     * @return
     *  http://127.0.0.1:9090/v1/catalog/categories
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public CreateResponseDto<BigInteger> createCategory(@Valid @RequestBody CategoryCreateRequestDto body){
        return catalogService.createCategory(body);
    }



}
