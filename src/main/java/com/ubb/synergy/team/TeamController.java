package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.CreateTeamDto;
import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.team.exception.TeamAlreadyExistException;
import com.ubb.synergy.team.exception.TeamNotFoundException;
import com.ubb.synergy.team.projection.TeamSummaryProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {this.teamService = teamService;}

    @PostMapping
    public ResponseEntity<?> saveTeam(@RequestBody CreateTeamDto teamDto){
        try{
            return ResponseEntity.ok(teamService.saveTeam(teamDto));
        }catch (TeamAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        try{
            teamService.deleteTeam(id);
            return ResponseEntity.ok().build();
        }catch (TeamNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryProjection>> findAllTeamsSummary(){
        return ResponseEntity.ok(teamService.findAllTeamsSummary());
    }
}
