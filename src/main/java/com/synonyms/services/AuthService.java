package com.synonyms.services;

import com.synonyms.entity.User;
import com.synonyms.exception.CommonException;
import com.synonyms.model.UserModel;
import com.synonyms.repository.UserRepo;
import com.synonyms.request.UserSignUpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class AuthService {

    private final ModelMapper mapper;
    private final UserRepo userRepositories;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(ModelMapper mapper, UserRepo userRepositories,
                       BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
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

}
