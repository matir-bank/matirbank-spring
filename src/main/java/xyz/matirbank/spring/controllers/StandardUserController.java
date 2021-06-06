package xyz.matirbank.spring.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import xyz.matirbank.spring.models.responses.base.BaseResponse;
import xyz.matirbank.spring.models.responses.base.ErrorResponse;
import xyz.matirbank.spring.services.StandardUserService;
import xyz.matirbank.spring.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/user")
@Api("User Controller")
public class StandardUserController {

    @Autowired
    StandardUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @ApiOperation("Login")
    public ResponseEntity<BaseResponse<JwtResponse>> loginUser(@RequestBody StandardUserLoginRequest request) {
        StandardUsers user = userService.loginUser(request);

        if (user != null) {
            String token = jwtTokenUtil.generateToken(user);
            JwtResponse jwtResponse = new JwtResponse(token);

            return new ResponseEntity<>(
                    new BaseResponse<>(200, jwtResponse, null),
                    new HttpHeaders(),
                    HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(10001);
            errorResponse.setSummary("Invalid Login Details");

            return new ResponseEntity<>(
                    new BaseResponse<>(200, null, errorResponse),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<StandardUsers>> createUser(@RequestBody StandardUserSignupRequest userCreateRequest) {

        if (userService.getUserByPhone(userCreateRequest.getPhone()) == null) {
            StandardUsers user = userService.createUser(userCreateRequest);
            return new ResponseEntity<>(
                    new BaseResponse<>(200, user, null),
                    new HttpHeaders(),
                    HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(10002);
            errorResponse.setSummary("User Already Exists");

            return new ResponseEntity<>(
                    new BaseResponse<>(200, null, errorResponse),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<BaseResponse<StandardUsers>> getUserProfile() {

        String hash = SecurityContextHolder.getContext().getAuthentication().getName();
        StandardUsers user = userService.getUserByHash(hash);
        return new ResponseEntity<>(
                new BaseResponse<>(200, user, null),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @GetMapping("/hash/{hash}")
    public ResponseEntity<BaseResponse<StandardUsers>> getUserByHash(@PathVariable String hash) {
        StandardUsers user = userService.getUserByHash(hash);
        if (user != null) {
            return new ResponseEntity<>(
                    new BaseResponse<>(200, user.toScopedData(), null),
                    new HttpHeaders(),
                    HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(10003);
            errorResponse.setSummary("User Does Not Exists");

            return new ResponseEntity<>(
                    new BaseResponse<>(200, null, errorResponse),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<BaseResponse<StandardUsers>> getUserByPhone(@PathVariable String phone) {
        StandardUsers user = userService.getUserByPhone(phone);
        if (user != null) {
            return new ResponseEntity<>(
                    new BaseResponse<>(200, user.toScopedData(), null),
                    new HttpHeaders(),
                    HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(10003);
            errorResponse.setSummary("User Does Not Exists");

            return new ResponseEntity<>(
                    new BaseResponse<>(200, null, errorResponse),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

}
