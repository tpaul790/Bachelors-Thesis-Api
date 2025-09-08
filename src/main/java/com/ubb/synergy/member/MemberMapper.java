package com.ubb.synergy.member;

import com.ubb.synergy.member.dto.CreateMemberDto;
import com.ubb.synergy.member.dto.MemberDto;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    //MemberDto
    MemberDto entityToDto(MemberEntity entity);

    List<MemberDto> entityToDto(List<MemberEntity> entities);

    MemberEntity dtoToEntity(MemberDto dto);

    //CreateMemberDto
    CreateMemberDto entitytoCreateDto(MemberEntity entity);

    List<CreateMemberDto> entitytoCreateDto(List<MemberEntity> entities);

    MemberEntity createDtoToEntity(CreateMemberDto dto);
}
