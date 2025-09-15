package com.ubb.synergy.user;

import com.ubb.synergy.project.ProjectService;
import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import com.ubb.synergy.security.annotations.AllowAdmin;
import com.ubb.synergy.team.TeamService;
import com.ubb.synergy.team.projection.TeamProjection;
import com.ubb.synergy.team.projection.TeamSummaryProjection;
import com.ubb.synergy.user.dto.AdminUpdateDto;
import com.ubb.synergy.user.dto.UserDto;
import com.ubb.synergy.user.dto.UserUpdateDto;
import com.ubb.synergy.user.exception.InvalidUserException;
import com.ubb.synergy.user.exception.UserAlreadyExistException;
import com.ubb.synergy.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ProjectService projectService;
    private final TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(userService.findUserById(id));
        }catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    @AllowAdmin
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){
        try {
            user.setUserRole(UserRole.USER);
            return ResponseEntity.ok(userService.saveUser(user));
        }catch (InvalidUserException | UserAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @AllowAdmin
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch(UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/user-{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto user){
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        }catch (UserNotFoundException | InvalidUserException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin-{id}")
    @AllowAdmin
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody AdminUpdateDto user){
        try {
            System.out.println(user);
            return ResponseEntity.ok(userService.updateUser(id, user));
        }catch (UserNotFoundException | InvalidUserException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<Page<ProjectSummaryProjection>> findAllProjectsByUserId(
            @PathVariable Long id,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ){
        return ResponseEntity.ok(projectService.findAllProjectsByUserId(id, pageNumber, pageSize));
    }

    @GetMapping("{id}/teams")
    public ResponseEntity<List<TeamProjection>> findAllTeamsByUserId(@PathVariable Long id){
        return ResponseEntity.ok(teamService.findAllTeamsByUserId(id));
    }

    @GetMapping("{id}/teams-summary")
    public ResponseEntity<Page<TeamSummaryProjection>> findAllTeamsSummaryByUserId(
            @PathVariable Long id,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ){
        return ResponseEntity.ok(teamService.findAllTeamsSummaryByUserId(id, pageNumber, pageSize));
    }

}
