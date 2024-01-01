package com.synonys.controller;

import com.synonys.repository.UserRepo;
import com.synonys.request.SynonymsCreateRequest;
import com.synonys.request.ChargeDataSearchRequest;
import com.synonys.response.ApplicationResponse;
import com.synonys.response.SynonymsCreationResponse;
import com.synonys.response.GenericPaginationResponse;
import com.synonys.services.SynonymsService;
import com.synonys.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.synonys.entity.Synonyms;
import com.synonys.repository.SynonymsRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v0/synonyms", produces = MediaType.APPLICATION_JSON_VALUE)
public class SynonymsController {

    private final UserRepo userRepo;
    private final SynonymsRepo synonymsRepo;
    private final SynonymsService synonymsService;
    private static final Logger log = LoggerFactory.getLogger(SynonymsController.class);

    public SynonymsController(UserRepo userRepo, SynonymsRepo synonymsRepo, SynonymsService synonymsService) {
        this.userRepo = userRepo;
        this.synonymsRepo = synonymsRepo;
        this.synonymsService = synonymsService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApplicationResponse<SynonymsCreationResponse>> createOrUpdateSynonyms(
            @RequestBody SynonymsCreateRequest request) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.createOrUpdateSynonym(request)));
    }

/*    @PostMapping("all")
    public ResponseEntity<ApplicationResponse<GenericPaginationResponse<SynonymsCreationResponse>>>
    getChargeData(@RequestBody ChargeDataSearchRequest request) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.fetchChargeData(request)));
    }

    @GetMapping("lastChargeTime")
    public ResponseEntity<ApplicationResponse<String>> getLastChargeTimeData(@RequestParam("uniqueId") String uniqueId) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.lastChargeTimeData(uniqueId)));
    }*/
}
