package com.ubb.synergy.team;

import com.ubb.synergy.team.projection.TeamProjection;
import com.ubb.synergy.team.projection.TeamSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Long>, JpaSpecificationExecutor<TeamEntity> {
    boolean existsByName(String name);

    @Query("""
        SELECT t
        FROM TeamEntity t
        JOIN t.members m
        WHERE m.user.id = :id AND m.role = 'MANAGER'
    """)
    List<TeamProjection> findAllTeamsByUserId(Long id);

    @Query("""
        SELECT t
        FROM TeamEntity t
        JOIN t.members m
        JOIN m.user u
        WHERE u.id = :id
    """)
    List<TeamSummaryProjection> findAllTeamsSummaryByUserId(Long id);
}
