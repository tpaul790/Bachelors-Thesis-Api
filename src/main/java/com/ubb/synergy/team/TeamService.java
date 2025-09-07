package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.team.exception.TeamAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper mapper;

    public TeamDto saveTeam(TeamDto teamDto){
        if(teamRepository.existsByName(teamDto.getName())){
            throw new TeamAlreadyExistException();
        }
        TeamEntity team = mapper.dtoToEntity(teamDto);
        return mapper.entityToDto(teamRepository.save(team));
    }
}
