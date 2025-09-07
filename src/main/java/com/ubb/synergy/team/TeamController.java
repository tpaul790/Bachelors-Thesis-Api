package com.ubb.synergy.team;

import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.team.exception.TeamAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {this.teamService = teamService;}

    @PostMapping
    public ResponseEntity<?> saveTeam(@RequestBody TeamDto teamDto){
        try{
            return ResponseEntity.ok(teamService.saveTeam(teamDto));
        }catch (TeamAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error",e.getMessage()));
        }
    }
}
