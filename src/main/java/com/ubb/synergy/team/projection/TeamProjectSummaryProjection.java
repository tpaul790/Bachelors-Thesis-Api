package com.ubb.synergy.team.projection;

import java.time.LocalDateTime;

public interface TeamProjectSummaryProjection {
    Long getId();
    String getName();
    LocalDateTime getCreatedAt();
    Integer[] getIcons();
}
