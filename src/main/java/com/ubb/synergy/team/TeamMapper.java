package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.CreateTeamDto;
import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.user.UserEntity;
import com.ubb.synergy.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    //TeamDto
    TeamDto entityToDto(TeamEntity entity);

    List<TeamDto> entityToDto(List<TeamEntity> entities);

    TeamEntity dtoToEntity(TeamDto dto);

    //CreateTeamDto
    CreateTeamDto entityToCreateDto(TeamEntity entity);

    List<CreateTeamDto> entityToCreateDto(List<TeamEntity> entities);

    TeamEntity createDtoToEntity(CreateTeamDto dto);

}
