package com.jpachalle.jpachallenge.repository.impl;

import com.jpachalle.jpachallenge.entity.QCategory;
import com.jpachalle.jpachallenge.repository.CategoryCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> getCategoryCode() {
        return queryFactory.select(QCategory.category.code).from(QCategory.category).fetch();
    }

//    @Override
//    public List<Category> getCategory(BigInteger categoryGroupId) {
//
//        return queryFactory.select(QCategory.category).from(QCategory.category)
//                .where(QCategory.category.categoryGroup.id.eq(categoryGroupId)).fetch();
//    }
}

