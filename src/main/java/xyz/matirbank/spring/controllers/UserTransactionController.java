package xyz.matirbank.spring.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.matirbank.spring.models.entities.UserTransactions;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;

@RestController
@RequestMapping("/api/transaction")
public class UserTransactionController {
    
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public BaseResponseEntity<UserTransactions> getTransactions() {
        UserTransactions userTransactions = new UserTransactions();
        return new BaseResponseEntity<>().basicData(userTransactions);
    }
    
}