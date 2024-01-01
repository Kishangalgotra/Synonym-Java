package com.synonys.controller;

import com.synonys.entity.User;
import com.synonys.exception.CommonException;
import com.synonys.repository.UserRepo;
import com.synonys.response.ApplicationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v0/charge_user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/verifyCode")
    public ResponseEntity<ApplicationResponse<String>> verifyUser(@RequestParam("email") String email,
                                                                  @RequestParam("code") String code) {
       /* boolean b = userRepo.existsByEmailIdAndUniqueId(email, code);
        if (!b) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "No User Found with provided email and password");
        }*/
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse("SUCCESS"));
    }

    @GetMapping("/signUp")
    public ResponseEntity<ApplicationResponse<String>> signUp(@RequestParam("email") String email,
                                                              @RequestParam("code") String code) {
        User user = new User();
        user.setEmailId(email);
        user.setUniqueId(code);
        userRepo.save(user);
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse("SUCCESS"));
    }
}
