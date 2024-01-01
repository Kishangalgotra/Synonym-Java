package com.synonyms.services;


import com.synonyms.config.CustomUserDetails;
import com.synonyms.entity.Authorities;
import com.synonyms.entity.User;
import com.synonyms.repository.AuthoritiesRepository;
import com.synonyms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepo userRepositories;

    @Autowired
    private final AuthoritiesRepository authoritiesRepository;

    public UserServiceImpl(UserRepo userRepositories, AuthoritiesRepository authoritiesRepository) {
        this.userRepositories = userRepositories;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositories.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("USER_NOT_FOUND", email)));

        List<Authorities> authorities = new ArrayList<>();//authoritiesRepository.findByUserRole(String.valueOf(user.getRole()));
        return new CustomUserDetails(user, authorities);
    }
}
