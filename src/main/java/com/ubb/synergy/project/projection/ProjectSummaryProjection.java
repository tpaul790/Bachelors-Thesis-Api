package com.ubb.synergy.project.projection;

import com.ubb.synergy.member.MemberRole;
import com.ubb.synergy.team.projection.TeamProjectSummaryProjection;

import java.time.LocalDateTime;

public interface ProjectSummaryProjection {
    Long getId();
    String getName();
    String getDescription();
    LocalDateTime getCreatedAt();
    TeamProjectSummaryProjection getTeam();
}
