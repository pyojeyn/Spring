package com.jpachalle.jpachallenge.repository;

import com.jpachalle.jpachallenge.entity.UserLinkingCommerce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserLinkingCommerceRepository extends JpaRepository<UserLinkingCommerce, BigInteger>{
    Optional<List<UserLinkingCommerce>> findByUserId(BigInteger userId);
}
