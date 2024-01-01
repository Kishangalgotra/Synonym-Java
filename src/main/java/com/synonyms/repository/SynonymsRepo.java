package com.synonyms.repository;

import com.synonyms.model.SearchSynonymDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synonyms.entity.Synonyms;

import java.util.List;

@Repository
public interface SynonymsRepo extends JpaRepository<Synonyms, Long>, JpaSpecificationExecutor<Synonyms> {

    @Query("select new com.synonyms.model.SearchSynonymDto(sh.synonyms, sh.synonymId) " +
            "from Synonyms as sh where sh.synonymId in (select sh2.synonymId from Synonyms as sh2" +
            " where sh2.synonyms IN (:synonyms1) order by sh2.id DESC)")
    List<SearchSynonymDto> findSynonymIdBySynonymsContaining(@Param("synonyms1") List<String> synonyms1);

    @Query(" select sh.synonyms from Synonyms as sh where sh.synonymId " +
            " IN (select synonymId from Synonyms where synonyms =:word order by id)")
    List<String> findByWord(@Param("word") String word);

    int deleteBySynonyms(@Param("word") String word);

    Synonyms findBySynonyms(@Param("word") String word);

    @Query("Select sh.synonyms from Synonyms sh where sh.synonymId = :id")
    List<String> findBySynonymId(@Param("id") String id);
}
