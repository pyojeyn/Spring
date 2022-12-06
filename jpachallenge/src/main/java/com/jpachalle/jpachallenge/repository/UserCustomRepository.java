package com.jpachalle.jpachallenge.repository;

import com.jpachalle.jpachallenge.dto.user.UserListRequestDto;
import com.jpachalle.jpachallenge.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {

    public Page<User> findAllSearch(UserListRequestDto dto, Pageable pageable);
}
