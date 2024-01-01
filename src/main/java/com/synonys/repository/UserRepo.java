package com.synonys.repository;

import com.synonys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    //boolean existsByEmailIdAndUniqueId(String email, String code);

    User findByEmailId(String email);

    //User findByEmailIdAndUniqueId(String email, String code);
}
