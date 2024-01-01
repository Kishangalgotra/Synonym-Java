package com.synonys.controller;

import com.synonys.entity.ChargePointOperator;
import com.synonys.repository.ChargePointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/chargePoint", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class ChargePointController {

    @Autowired
    private ChargePointRepo chargePointRepo;

    @GetMapping("/all")
    public ResponseEntity<List<ChargePointOperator>> getAllChargePoint() {
        return ResponseEntity.ok(chargePointRepo.findAll());
    }
}
