package com.jpachalle.jpachallenge.service;

import com.jpachalle.jpachallenge.dto.ListItemResponseDto;
import com.jpachalle.jpachallenge.dto.user.UserListRequestDto;
import com.jpachalle.jpachallenge.dto.user.UserListResponseDto;
import com.jpachalle.jpachallenge.dto.user.UserResponseDto;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public UserResponseDto getUserInfo();

    public ListItemResponseDto<UserListResponseDto> getUserList(UserListRequestDto body, Pageable pageable);
}
