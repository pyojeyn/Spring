package com.jpachalle.jpachallenge.mapper;

import com.jpachalle.jpachallenge.dto.user.UserListResponseDto;
import com.jpachalle.jpachallenge.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserListResponseDto toListDto(User entity);
}
