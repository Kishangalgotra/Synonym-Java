package com.synonys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.synonys.entity.Synonyms;

@Repository
public interface SynonymsRepo extends JpaRepository<Synonyms, Long>, JpaSpecificationExecutor<Synonyms> {

    @Query("select sh from Synonyms as sh where sh.synonyms like '% :synonyms%'")
    Synonyms findBySynonymsContaining(String synonyms);
}
