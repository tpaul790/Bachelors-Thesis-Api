package com.ubb.synergy.member;

import com.ubb.synergy.member.dto.CreateMemberDto;
import com.ubb.synergy.member.dto.MemberDto;
import com.ubb.synergy.member.exception.MemberAlreadyExistException;
import com.ubb.synergy.team.TeamEntity;
import com.ubb.synergy.user.UserEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper mapper;
    private final EntityManager entityManager;

    public MemberDto saveMember(CreateMemberDto createMemberDto) {
        if(memberRepository.existsByUserIdAndTeamId(createMemberDto.getUserId(), createMemberDto.getTeamId())){
            throw new MemberAlreadyExistException();
        }

        MemberEntity member = new MemberEntity();
        member.setRole(createMemberDto.getRole());
        member.setUser(entityManager.getReference(UserEntity.class, createMemberDto.getUserId()));
        member.setTeam(entityManager.getReference(TeamEntity.class, createMemberDto.getTeamId()));

        return mapper.entityToDto(memberRepository.save(member));
    }

}
