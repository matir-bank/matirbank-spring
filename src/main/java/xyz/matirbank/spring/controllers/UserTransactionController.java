package xyz.matirbank.spring.controllers;

import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.matirbank.spring.models.Enums.TransactionType;
import xyz.matirbank.spring.models.entities.StandardUsers;
import xyz.matirbank.spring.models.entities.UserTransactions;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;
import xyz.matirbank.spring.repositories.UserTransactionRepository;
import xyz.matirbank.spring.services.StandardUserService;

@RestController
@RequestMapping("/api/transaction")
public class UserTransactionController {
    
    @Autowired
    StandardUserService standardUserController;
    
    @Autowired
    UserTransactionRepository userTransactionRepository;
    
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/send-money")
    public ResponseEntity<BaseResponseEntity<UserTransactions>> sendMoney(String user_hash, Double amount, String remarks) {
        StandardUsers senderUser = standardUserController.getCurrentUser();
        StandardUsers receiverUser = standardUserController.getUserByHash(user_hash);
        
        UserTransactions userTransaction = new UserTransactions();
        userTransaction.setTransaction_id("X");
        userTransaction.setUser_from(senderUser.getId());
        userTransaction.setUser_to(receiverUser.getId());
        userTransaction.setAmount(amount);
        userTransaction.setTransaction_type(TransactionType.USER_TO_USER_TRANSACTION);
        
        userTransaction = userTransactionRepository.save(userTransaction);
        return new BaseResponseEntity<>().basicData(userTransaction).getEntity();
    }
    
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public ResponseEntity<BaseResponseEntity<List<UserTransactions>>> getTransactions() {
        StandardUsers user = standardUserController.getCurrentUser();
        List<UserTransactions> userTransactions = userTransactionRepository.getUserTransactions(user.getId());
        return new BaseResponseEntity<>().basicData(userTransactions).getEntity();
    }
    
    
    
}