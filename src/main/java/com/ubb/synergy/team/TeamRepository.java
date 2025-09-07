package com.ubb.synergy.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Long>, JpaSpecificationExecutor<TeamEntity> {
    boolean existsByName(String name);
}
