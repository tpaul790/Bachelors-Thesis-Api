package com.ubb.synergy.user;

import com.ubb.synergy.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(UserEntity userEntity);

    List<UserDto> entityToDto(List<UserEntity> userEntities);

    UserEntity dtoToEntity(UserDto dto);
}
