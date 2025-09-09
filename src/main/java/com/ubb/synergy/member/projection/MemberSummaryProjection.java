package com.ubb.synergy.member.projection;

import com.ubb.synergy.member.MemberRole;
import com.ubb.synergy.user.projection.UserSummaryProjection;

public interface MemberSummaryProjection {
    Long getId();
    MemberRole getRole();
    UserSummaryProjection getUser();
}
