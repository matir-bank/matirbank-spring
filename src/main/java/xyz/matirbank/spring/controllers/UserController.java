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
import xyz.matirbank.spring.entities.User;
import xyz.matirbank.spring.entities.UserRequest;
import xyz.matirbank.spring.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User userResponse = userService.createUser(userRequest);
        return new ResponseEntity<>(userResponse, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/phone/{phone}")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phone) {
        User userResponse = userService.getUserByPhone(phone);
        return new ResponseEntity<>(userResponse, new HttpHeaders(), HttpStatus.OK);
    }
    
}
