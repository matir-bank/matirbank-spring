package xyz.matirbank.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.matirbank.spring.entities.User;
import xyz.matirbank.spring.repositories.UserRepository;

@RestController
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/user/list")
    public ResponseEntity<List<User>> getUserList() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }
    
}
