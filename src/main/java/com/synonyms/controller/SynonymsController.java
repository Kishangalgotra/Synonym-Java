package com.synonyms.controller;

import com.synonyms.repository.UserRepo;
import com.synonyms.request.SynonymsCreateRequest;
import com.synonyms.response.ApplicationResponse;
import com.synonyms.response.SynonymsCreationResponse;
import com.synonyms.services.SynonymsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.synonyms.repository.SynonymsRepo;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<ApplicationResponse<SynonymsCreationResponse>> createOrUpdateSynonyms(
            @RequestBody SynonymsCreateRequest request) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.createOrUpdateSynonym(request)));
    }

    @GetMapping
    public ResponseEntity<ApplicationResponse<List<String>>> getSynonyms(@RequestParam("word") String word) {
        List<String> synonymsList = synonymsService.fetchSynonyms(word);
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsList));
    }

    @DeleteMapping
    public ResponseEntity<ApplicationResponse<List<String>>> deleteSynonyms(@RequestParam("word") String word) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.deleteSynonym(word)));
    }
}
