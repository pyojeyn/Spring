package com.jpachalle.jpachallenge.repository.impl;

import com.jpachalle.jpachallenge.repository.UserCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;
}
