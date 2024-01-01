package com.synonyms.repository;

import com.synonyms.entity.Authorities;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>, JpaSpecificationExecutor<Authorities> {

    @Cacheable(value = "applicationCache", key = "'config:' + #role", unless = "#result==null")
    @Query("Select authorities from Authorities as authorities where authorities.userRole = :role")
    List<Authorities> findByUserRole(@Param("role") String role);
}
