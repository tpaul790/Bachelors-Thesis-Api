package com.ubb.synergy.user;

import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {

    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);


    List<UserEntity> findAllByOrderById();
}
