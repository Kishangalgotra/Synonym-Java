package com.synonyms.services;

import com.synonyms.config.CustomUserDetails;
import com.synonyms.controller.SynonymsController;
import com.synonyms.entity.Authorities;
import com.synonyms.entity.User;
import com.synonyms.exception.CommonException;
import com.synonyms.model.UserModel;
import com.synonyms.repository.AuthoritiesRepository;
import com.synonyms.repository.UserRepo;
import com.synonyms.request.UserSignUpRequest;
import com.synonyms.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final ModelMapper mapper;
    private final UserRepo userRepo;
    private final UserRepo userRepositories;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthoritiesRepository authoritiesRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthService(ModelMapper mapper, UserRepo userRepo, UserRepo userRepositories,
                       BCryptPasswordEncoder passwordEncoder, AuthoritiesRepository authoritiesRepository) {
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public UserModel singUpUser(UserSignUpRequest request) {
        try {
            boolean b = userRepositories.existsByEmailId(request.getEmail());
            if (b)
                throw new CommonException(HttpStatus.BAD_REQUEST, "User already registered with given email and phone");
            User user = new User();
            user.setEmailId(request.getEmail());
            user.setRole(request.getRole());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepositories.save(user);
            return mapper.map(user, UserModel.class);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Boolean hasAuthority(HttpServletRequest request, String authority) throws AccessDeniedException {
        try {
            System.out.println("dasdasd");
            OAuth2Authentication userPrincipal = (OAuth2Authentication) request.getUserPrincipal();
            if (userPrincipal == null) {
                throw new CommonException(HttpStatus.UNAUTHORIZED, "Please provide the valid authorization token");
            }
            User user = retrieveUser(request);
            if (user.getAuthorities().isEmpty() || !user.getAuthorities().contains(authority)) {
                log.error("User does not have adequate authority");
                throw new AccessDeniedException("User does not have adequate authority");
            }
            request.setAttribute("userObject", user);
            return true;
        } catch (Exception e) {
            throw new AccessDeniedException(e.getMessage());
        }
    }

    public User retrieveUser(HttpServletRequest request) throws CommonException {
        OAuth2Authentication userPrincipal = (OAuth2Authentication) request.getUserPrincipal();
        if (userPrincipal == null) {
            throw new CommonException(HttpStatus.UNAUTHORIZED, "Please provide the valid authorization token");
        }
        CustomUserDetails defaultUserDetails =
                (CustomUserDetails) ((OAuth2Authentication) request.getUserPrincipal()).getUserAuthentication()
                        .getPrincipal();
        User user = defaultUserDetails.getUser();//retrieveUser(defaultUserDetails.getUser().getEmailId());
        Collection<? extends GrantedAuthority> authorities = defaultUserDetails.getAuthorities();
        user.setAuthorities(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            token = (StringUtils.isNotBlank(request.getParameter("access_token")) ? request.getParameter(
                    "access_token") : request.getParameter("accessToken"));
            if (StringUtils.isNotBlank(token))
                token = "Bearer" + token;
        }
        return user;
    }

    public User retrieveUser(String userName) throws CommonException {

        User optionalUserData = userRepo.findByEmailId(userName).get();
        if (CommonUtils.isNull(optionalUserData))
            throw new CommonException(HttpStatus.NOT_FOUND, "Please enter registered email");

        List<String> authorities
                = authoritiesRepository.findByUserRoleId(String.valueOf(optionalUserData.getRole()));
        optionalUserData.setAuthorities(authorities);
        return optionalUserData;
    }
}
