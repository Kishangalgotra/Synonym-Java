package com.synonyms.controller;

import com.synonyms.model.UserModel;
import com.synonyms.request.UserSignUpRequest;
import com.synonyms.response.ApplicationResponse;
import com.synonyms.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;
    private static final Logger log = LoggerFactory.getLogger(SynonymsController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<ApplicationResponse<UserModel>> signUp(@Valid  @RequestBody UserSignUpRequest userSignUpRequest) {
        log.info("Request received to create admin credentials :{}", userSignUpRequest.getEmail());
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(authService.singUpUser(userSignUpRequest)));
    }

}
