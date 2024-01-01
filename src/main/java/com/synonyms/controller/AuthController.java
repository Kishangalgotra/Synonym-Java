package com.synonyms.controller;

import com.synonyms.config.CustomUserDetails;
import com.synonyms.entity.Authorities;
import com.synonyms.entity.User;
import com.synonyms.exception.CommonException;
import com.synonyms.model.UserModel;
import com.synonyms.repository.AuthoritiesRepository;
import com.synonyms.repository.UserRepo;
import com.synonyms.request.UserSignUpRequest;
import com.synonyms.response.ApplicationResponse;
import com.synonyms.services.AuthService;
import com.synonyms.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UserRepo userRepo;
    private final AuthService authService;
    private final AuthoritiesRepository authoritiesRepository;
    private static final Logger log = LoggerFactory.getLogger(SynonymsController.class);

    public AuthController(UserRepo userRepo, AuthService authService, AuthoritiesRepository authoritiesRepository) {
        this.userRepo = userRepo;
        this.authService = authService;
        this.authoritiesRepository = authoritiesRepository;
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<ApplicationResponse<UserModel>> signUp(@Valid  @RequestBody UserSignUpRequest userSignUpRequest) {
        log.info("Request received to create admin credentials :{}", userSignUpRequest.getEmail());
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(authService.singUpUser(userSignUpRequest)));
    }
}
