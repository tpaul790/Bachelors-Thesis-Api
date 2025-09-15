package com.ubb.synergy.team.projection;

import com.ubb.synergy.member.projection.MemberSummaryProjection;

import java.time.LocalDateTime;

public interface TeamProjectSummaryProjection {
    Long getId();
    String getName();
    LocalDateTime getCreatedAt();
    MemberSummaryProjection[] getMembers();
}
