package xyz.matirbank.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.matirbank.spring.models.Enums;
import xyz.matirbank.spring.models.Enums.ServiceCharge;
import xyz.matirbank.spring.models.Enums.TransactionType;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.entities.UserTransaction;
import xyz.matirbank.spring.repositories.UserTransactionRepository;
import xyz.matirbank.spring.utils.Commons;

public class UserTransactionService {
    
    @Autowired
    StandardUserService standardUserService;
    
    @Autowired
    UserTransactionRepository userTransactionRepository;
    
    public List<UserTransaction> getUserTransactions(Long user_id) {
        return userTransactionRepository.getUserTransactions(user_id);
    }
    
    public UserTransaction makeNewUserTransaction(StandardUser senderUser, StandardUser receiverUser, Double amount, String remarks) {
        UserTransaction userTransaction = new UserTransaction();
        
        // Generate Unique Transaction ID
        String transaction_id = Commons.getRandomAlphaNumeric(12);
        while(userTransactionRepository.findTransactionByTransactionId(transaction_id) != null) {
            transaction_id = Commons.getRandomAlphaNumeric(12);
        }
        
        userTransaction.setTransaction_id(transaction_id);
        userTransaction.setUser_from(senderUser.getId());
        userTransaction.setUser_to(receiverUser.getId());
        userTransaction.setAmount(amount);
        userTransaction.setRemarks(remarks);
        userTransaction.setTransaction_type(TransactionType.USER_TO_USER_TRANSACTION);
        
        userTransaction = userTransactionRepository.save(userTransaction);
        return userTransaction;
    }
    
    public UserTransaction makeNewServiceChargeTransaction(StandardUser senderUser, Double amount, ServiceCharge serviceCharge) {
        UserTransaction userTransaction = new UserTransaction();
        
        // Generate Unique Transaction ID
        String transaction_id = Commons.getRandomAlphaNumeric(12);
        while(userTransactionRepository.findTransactionByTransactionId(transaction_id) != null) {
            transaction_id = Commons.getRandomAlphaNumeric(12);
        }
        
        userTransaction.setTransaction_id(transaction_id);
        userTransaction.setUser_from(senderUser.getId());
        userTransaction.setUser_to(standardUserService.getSystemUser().getId());
        userTransaction.setAmount(amount);
        userTransaction.setService_charge(serviceCharge);
        userTransaction.setTransaction_type(TransactionType.USER_TO_SERVICE_CHARGE);
        
        userTransaction = userTransactionRepository.save(userTransaction);
        return userTransaction;
    }
    
    
    
}
