package xyz.matirbank.spring.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.matirbank.spring.models.entities.StandardUsers;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.models.requests.StandardUserLoginRequest;
import xyz.matirbank.spring.models.responses.JwtResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;
import xyz.matirbank.spring.services.StandardUserService;
import xyz.matirbank.spring.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/user")
public class StandardUserController {

    @Autowired
    StandardUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody StandardUserLoginRequest request) {
        StandardUsers user = userService.loginUser(request);
        if (user != null) {
            String token = jwtTokenUtil.generateToken(user);
            JwtResponse jwtResponse = new JwtResponse(token);
            return new BaseResponseEntity<>().basicData(jwtResponse).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1001, "Invalid Login Details").getEntity();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody StandardUserSignupRequest userCreateRequest) {
        if (userService.getUserByPhone(userCreateRequest.getPhone()) == null) {
            StandardUsers user = userService.createUser(userCreateRequest);
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1002, "User Already Exists").getEntity();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/profile")
    public ResponseEntity getUserProfile() {
        String hash = SecurityContextHolder.getContext().getAuthentication().getName();
        StandardUsers user = userService.getUserByHash(hash);
        return new BaseResponseEntity<>().basicData(user).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hash/{hash}")
    public ResponseEntity getUserByHash(@PathVariable String hash) {
        StandardUsers user = userService.getUserByHash(hash).toScopedData();
        if (user != null) {
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1003, "User Does Not Exists").getEntity();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/phone/{phone}")
    public ResponseEntity getUserByPhone(@PathVariable String phone) {
        StandardUsers user = userService.getUserByPhone(phone).toScopedData();
        if (user != null) {
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1003, "User Does Not Exists").getEntity();
        }
    }

}
