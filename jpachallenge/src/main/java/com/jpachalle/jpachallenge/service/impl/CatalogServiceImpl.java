package com.jpachalle.jpachallenge.service.impl;

import com.jpachalle.jpachallenge.dto.CreateResponseDto;
import com.jpachalle.jpachallenge.dto.SuccessResponseDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupUpdateRequestDto;
import com.jpachalle.jpachallenge.entity.*;
import com.jpachalle.jpachallenge.mapper.CatalogMapper;
import com.jpachalle.jpachallenge.repository.*;
import com.jpachalle.jpachallenge.service.CatalogService;
import com.jpachalle.jpachallenge.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {
    private final CategoryRepository categoryRepository;
    private final CategoryGroupRepository categoryGroupRepository;
    private final CodeService codeService;
    private final UserLinkingCommerceRepository userLinkingCommerceRepository;
    private final MatchedCategoryRepository matchedCategoryRepository;

    @Override
    public CreateResponseDto<BigInteger> createCategoryGroup(CategoryGroupCreateRequestDto body) {

        // 리퀘스트 DTO 를 mapStruct 을 이용해 entity 로 변경
        CategoryGroup categoryGroup = CatalogMapper.INSTANCE.toEntity(body);

        // 변경된 entity 저장
        categoryGroupRepository.save(categoryGroup);
        //CreateResponseDto<T>
        // 타입.<제네릭타입>builder()
        return CreateResponseDto.<BigInteger>builder().id(categoryGroup.getId()).build();
    }

    @SneakyThrows
    @Override
    public SuccessResponseDto updateCategoryGroup(CategoryGroupUpdateRequestDto body, BigInteger categoryGroupId) {
        CategoryGroup categoryGroup = categoryGroupRepository.findById(categoryGroupId).orElseThrow(()-> new NullPointerException());

        // 엔티티를 받아온 디티오로 수정.
        CatalogMapper.INSTANCE.updateFromDto(body, categoryGroup);

        // 수정 끝나고 저장.
        categoryGroupRepository.save(categoryGroup);

        return SuccessResponseDto.builder().success(true).build();
    }

    @SneakyThrows
    @Override
    public CreateResponseDto<BigInteger> createCategory(CategoryCreateRequestDto body) {
        String code = codeService.createCode(categoryRepository.getCategoryCode());

        Category category = CatalogMapper.INSTANCE.toEntity(body,
                categoryGroupRepository.findById(body.getCategoryGroupId())
                        .orElseThrow(() -> new Exception()));

        category.setCode(code);
        categoryRepository.save(category);

        //  **교훈
        // 1. 자식 엔티티 생성할 때 toEntity 매개변수에다가 부모엔티티들 넣어주기 꼮 !
        // 2. 빌드할때 먼저 Mapper 부터 추가하고 빌드하기 ! Mapper 추가하고 빌드 안하고 바로 그 Mapper 메소드 호출하는 코드 작성하면 아직 그 매퍼 구현체 안생겼는데 저 코드 실행하려 해서
        //    [Several possible source properties for target property "id".] 이런 에러남. ;
        if (body.getMatchedCategories() != null){
            List<MatchedCategory> matchedCategories =
                    body.getMatchedCategories().stream().
                            map((matchedCategory) -> CatalogMapper.INSTANCE.toEntity(userLinkingCommerceRepository.findById(matchedCategory.getUserLinkingCommerceId()).orElseThrow()
                                    , category, matchedCategory.getLinkingCommerceCategoryId(),  matchedCategory.getLinkingCommerceCategoryName())).collect(Collectors.toList());

            matchedCategoryRepository.saveAll(matchedCategories);
        }
        /*
        * insert into
            j_category
            (category_group_id, code, name)
            values
            (?, ?, ?)

             insert
        into
            j_matched_category
            (category_id, linking_commerce_category_id, linking_commerce_category_name, user_linking_commerce_id)
        values
            (?, ?, ?, ?)

        * */


        return null;

    }
}
