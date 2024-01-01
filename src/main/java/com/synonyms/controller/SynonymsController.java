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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.synonyms.repository.SynonymsRepo;

import javax.servlet.http.HttpServletRequest;
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
    @PreAuthorize("@authService.hasAuthority(#httpServletRequest, 'CREATE_SYNONYM')")
    public ResponseEntity<ApplicationResponse<SynonymsCreationResponse>> createOrUpdateSynonyms(
            HttpServletRequest httpServletRequest, @RequestBody SynonymsCreateRequest request) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.createOrUpdateSynonym(request)));
    }

    @GetMapping
    @PreAuthorize("@authService.hasAuthority(#httpServletRequest, 'VIEW_SYNONYM')")
    public ResponseEntity<ApplicationResponse<List<String>>> getSynonyms(
            HttpServletRequest httpServletRequest, @RequestParam("word") String word) {
        List<String> synonymsList = synonymsService.fetchSynonyms(word);
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsList));
    }

    @DeleteMapping
    @PreAuthorize("@authService.hasAuthority(#httpServletRequest, 'DELETE_SYNONYM')")
    public ResponseEntity<ApplicationResponse<List<String>>> deleteSynonyms(
            HttpServletRequest httpServletRequest, @RequestParam("word") String word) {
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(synonymsService.deleteSynonym(word)));
    }
}
