package com.ubb.synergy.project;

import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>, JpaSpecificationExecutor<ProjectEntity> {
    boolean existsByName(String name);

    @Query(
        value = """
            SELECT DISTINCT (p)
            FROM ProjectEntity p
            JOIN p.team t
            JOIN t.members m
            WHERE m.user.id = :id
            ORDER BY p.createdAt DESC
        """,
        countQuery = """
            SELECT COUNT(DISTINCT(p))
            FROM ProjectEntity p
            JOIN p.team t
            JOIN t.members m
            WHERE m.user.id = :id
        """
    )
    Page<ProjectSummaryProjection> findAllProjectsByUserId(Long id, Pageable pageable);

    @Query(
            value = """
            SELECT DISTINCT (p)
            FROM ProjectEntity p
            ORDER BY p.createdAt DESC
        """,
            countQuery = """
            SELECT COUNT(DISTINCT(p))
            FROM ProjectEntity p
        """
    )
    Page<ProjectSummaryProjection> findAllProjectsSummary(Pageable pageable);
}
