package com.jpachalle.jpachallenge.service.impl;

import com.jpachalle.jpachallenge.dto.ListItemResponseDto;
import com.jpachalle.jpachallenge.dto.user.UserListRequestDto;
import com.jpachalle.jpachallenge.dto.user.UserListResponseDto;
import com.jpachalle.jpachallenge.dto.user.UserResponseDto;
import com.jpachalle.jpachallenge.entity.User;
import com.jpachalle.jpachallenge.mapper.UserMapper;
import com.jpachalle.jpachallenge.repository.UserRepository;
import com.jpachalle.jpachallenge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SecurityUserServiceImpl securityUserService;

    @Override
    public UserResponseDto getUserInfo() {
        return null;
    }

    @Override
    public ListItemResponseDto<UserListResponseDto> getUserList(UserListRequestDto body, Pageable pageable) {

        // 레포지토리 구현하기 .
        Page<User> findUsers = userRepository.findAllSearch(body, pageable);

        List<UserListResponseDto> items = findUsers.get().map(s -> UserMapper.INSTANCE.toListDto(s)).collect(Collectors.toList());

        return ListItemResponseDto.<UserListResponseDto>builder().items(items)
                .total(findUsers.getTotalElements()).count(findUsers.getNumberOfElements())
                .limit(pageable.getPageSize()).offset(pageable.getOffset()).build();
    }
}
