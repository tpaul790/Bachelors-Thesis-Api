package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.CreateTeamDto;
import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.team.exception.TeamAlreadyExistException;
import com.ubb.synergy.team.exception.TeamNotFoundException;
import com.ubb.synergy.team.projection.TeamProjection;
import com.ubb.synergy.team.projection.TeamSummaryProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<TeamProjection> findAllTeamsByUserId(Long id) {
        return teamRepository.findAllTeamsByUserId(id);
    }

    public Page<TeamSummaryProjection> findAllTeamsSummaryByUserId(Long id, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return teamRepository.findAllTeamsSummaryByUserId(id, pageable);
    }

    public Page<TeamSummaryProjection> findAllTeamsSummary(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return teamRepository.findAllTeamsSummary(pageable);
    }
}
