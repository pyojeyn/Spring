package com.jpachalle.jpachallenge.repository;

import com.jpachalle.jpachallenge.entity.Category;
import com.jpachalle.jpachallenge.entity.CategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, BigInteger>, CategoryCustomRepository {
    Optional<Category> findByCode(String code);
}
