package com.jpachalle.jpachallenge.repository;

import com.jpachalle.jpachallenge.entity.CategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CategoryGroupRepository extends JpaRepository<CategoryGroup, BigInteger> {
}
