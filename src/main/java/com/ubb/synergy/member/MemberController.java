package com.ubb.synergy.member;

import com.ubb.synergy.member.dto.CreateMemberDto;
import com.ubb.synergy.member.exception.MemberAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
                    .body(e.getMessage());
        }
    }
}
