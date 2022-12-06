package com.jpachalle.jpachallenge.mapper;


import com.jpachalle.jpachallenge.dto.catalog.CategoryCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupCreateRequestDto;
import com.jpachalle.jpachallenge.dto.catalog.CategoryGroupUpdateRequestDto;
import com.jpachalle.jpachallenge.entity.Category;
import com.jpachalle.jpachallenge.entity.CategoryGroup;
import com.jpachalle.jpachallenge.entity.MatchedCategory;
import com.jpachalle.jpachallenge.entity.UserLinkingCommerce;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    CategoryGroup toEntity(CategoryGroupCreateRequestDto dto);

    @Mappings({@Mapping(source = "dto.name", target = "name"),
            @Mapping(source = "categoryGroup", target = "categoryGroup")})
    Category toEntity(CategoryCreateRequestDto dto, CategoryGroup categoryGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CategoryGroupUpdateRequestDto dto, @MappingTarget CategoryGroup entity);


//    @Mapping(target = "linkingCommerceCategoryName",
//            qualifiedByName = "setLinkingCommerceCategoryName")
//    MatchedCategory toEntity(UserLinkingCommerce userLinkingCommerce, Category category,
//                             String linkingCommerceCategoryId, List<String> linkingCommerceCategoryName);

    @Named("setLinkingCommerceCategoryName")
    default String createLinkingCommerceCategoryName(List<String> linkingCommerceCategoryName) {
        if (linkingCommerceCategoryName != null)
            return String.join(" > ", linkingCommerceCategoryName);
        return null;
    }
}