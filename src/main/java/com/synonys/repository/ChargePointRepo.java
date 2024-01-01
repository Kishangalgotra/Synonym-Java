package com.synonys.repository;

import com.synonys.entity.ChargePointOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargePointRepo extends JpaRepository<ChargePointOperator, Long> {

}
