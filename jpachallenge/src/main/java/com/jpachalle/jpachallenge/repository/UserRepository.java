package com.jpachalle.jpachallenge.repository;

import com.jpachalle.jpachallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<User, BigInteger> , UserCustomRepository{
}
