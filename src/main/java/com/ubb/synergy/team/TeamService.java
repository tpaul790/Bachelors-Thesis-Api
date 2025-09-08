package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.CreateTeamDto;
import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.team.exception.TeamAlreadyExistException;
import com.ubb.synergy.team.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper mapper;

    public TeamDto saveTeam(CreateTeamDto teamDto){
        if(teamRepository.existsByName(teamDto.getName())){
            throw new TeamAlreadyExistException();
        }
        TeamEntity team = mapper.createDtoToEntity(teamDto);
        team.setCreatedAt(LocalDateTime.now());

        return mapper.entityToDto(teamRepository.save(team));
    }

    public void deleteTeam(Long id) {
        if(!teamRepository.existsById(id)){
            throw new TeamNotFoundException();
        }
        teamRepository.deleteById(id);
    }
}
