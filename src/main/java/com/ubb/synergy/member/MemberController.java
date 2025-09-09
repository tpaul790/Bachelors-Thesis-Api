package com.ubb.synergy.member;

import com.ubb.synergy.member.dto.CreateMemberDto;
import com.ubb.synergy.member.exception.MemberAlreadyExistException;
import com.ubb.synergy.member.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {this.memberService = memberService;}

    @PostMapping
    public ResponseEntity<?> saveMember(@RequestBody CreateMemberDto dto){
        try{
            return ResponseEntity.ok(memberService.saveMember(dto));
        }catch(MemberAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id){
        try{
            memberService.deleteMember(id);
            return ResponseEntity.ok().build();
        }catch (MemberNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
