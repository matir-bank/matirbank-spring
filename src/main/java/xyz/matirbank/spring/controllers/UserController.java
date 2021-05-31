package xyz.matirbank.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.matirbank.spring.models.entities.User;
import xyz.matirbank.spring.models.requests.UserCreateRequest;
import xyz.matirbank.spring.models.requests.UserLoginRequest;
import xyz.matirbank.spring.models.responses.UserInfoResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponse;
import xyz.matirbank.spring.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<User>> loginUser(@RequestBody UserLoginRequest request) {
        User user = userService.loginUser(request);
        if(user != null) {
            
        }
        return new ResponseEntity<>(
                new BaseResponse<User>(200, user, null),
                new HttpHeaders(), 
                HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<User>> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.createUser(userCreateRequest);
        return new ResponseEntity<>(
                new BaseResponse<User>(200, user, null),
                new HttpHeaders(), 
                HttpStatus.OK);
    }
    
    @GetMapping("/hash/{hash}")
    public ResponseEntity<BaseResponse<UserInfoResponse>> getUserByHash(@PathVariable String hash) {
        User user = userService.getUserByHash(hash);
        return new ResponseEntity<>(
                new BaseResponse<UserInfoResponse>(200, UserInfoResponse.fromUser(user), null),
                new HttpHeaders(), 
                HttpStatus.OK);
    }
    
    @GetMapping("/phone/{phone}")
    public ResponseEntity<BaseResponse<UserInfoResponse>> getUserByPhone(@PathVariable String phone) {
        User user = userService.getUserByPhone(phone);
        return new ResponseEntity<>(
                new BaseResponse<UserInfoResponse>(200, UserInfoResponse.fromUser(user), null),
                new HttpHeaders(), 
                HttpStatus.OK);
    }
    
}
