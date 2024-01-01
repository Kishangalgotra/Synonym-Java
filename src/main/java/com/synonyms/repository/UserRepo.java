package com.synonyms.repository;

import com.synonyms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    //boolean existsByEmailIdAndUniqueId(String email, String code);

    Optional<User> findByEmailId(String email);

    boolean existsByEmailId(@Param("email") String email);

    //User findByEmailIdAndUniqueId(String email, String code);
}
