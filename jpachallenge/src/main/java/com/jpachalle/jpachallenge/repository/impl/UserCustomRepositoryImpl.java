package com.jpachalle.jpachallenge.repository.impl;

import com.jpachalle.jpachallenge.dto.user.UserListRequestDto;
import com.jpachalle.jpachallenge.entity.QUser;
import com.jpachalle.jpachallenge.entity.User;
import com.jpachalle.jpachallenge.repository.UserCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<User> findAllSearch(UserListRequestDto dto, Pageable pageable) {

        JPAQuery<User> q = jpaQueryFactory
                .select(Projections.constructor(User.class, QUser.user.emailAddress, QUser.user.name, QUser.user.createDateTime))
                .from(QUser.user);

        JPAQuery<Long> qCnt = jpaQueryFactory.select(QUser.user.count()).from(QUser.user); // 총 개수.

        if (dto.getEmailAddress() != null) {

            BooleanExpression expr = QUser.user.emailAddress.like(dto.getEmailAddress());


            q.where(expr);
            qCnt.where(expr);


        }

        List<User> fUser = q.offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(QUser.user.createDateTime.desc()).fetch();


        Long total = qCnt.fetchFirst();

        return new PageImpl<>(fUser, pageable, total);
    }
}
