package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.user.UserEntity;
import com.ubb.synergy.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto entityToDto(TeamEntity entity);

    List<TeamDto> entityToDto(List<TeamEntity> entities);

    TeamEntity dtoToEntity(TeamDto dto);
}
