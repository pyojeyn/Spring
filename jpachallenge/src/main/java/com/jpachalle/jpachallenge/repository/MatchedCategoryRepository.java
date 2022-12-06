package com.jpachalle.jpachallenge.repository;

import com.jpachalle.jpachallenge.entity.MatchedCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MatchedCategoryRepository extends JpaRepository<MatchedCategory, BigInteger> {
}
