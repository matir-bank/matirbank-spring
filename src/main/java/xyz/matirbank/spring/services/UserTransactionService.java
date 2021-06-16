package xyz.matirbank.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.models.Enums.ServiceCharge;
import xyz.matirbank.spring.models.Enums.TransactionType;
import xyz.matirbank.spring.models.ReturnContainer;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.entities.UserTransaction;
import xyz.matirbank.spring.repositories.UserTransactionRepository;
import xyz.matirbank.spring.utils.Commons;
import xyz.matirbank.spring.utils.StandardErrors;

@Service
public class UserTransactionService {

    @Autowired
    StandardUserService standardUserService;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    public ReturnContainer<List<UserTransaction>> getUserTransactions(Long user_id) {
        List<UserTransaction> listTransactions = userTransactionRepository.getUserTransactions(user_id);
        return new ReturnContainer(listTransactions);
    }

    public ReturnContainer<UserTransaction> makeNewUserTransaction(StandardUser senderUser, StandardUser receiverUser, Double amount, String remarks) {
        UserTransaction userTransaction = new UserTransaction();

        // Generate Unique Transaction ID
        String transaction_id = Commons.getRandomAlphaNumeric(12);
        while (userTransactionRepository.findUserTransactionByTransactionId(transaction_id) != null) {
            transaction_id = Commons.getRandomAlphaNumeric(12);
        }
        
        // Generate Unique Hash
        String unique_hash = Commons.makeRandomHash();
        while (userTransactionRepository.findUserTransactionByHash(unique_hash) != null) {
            unique_hash = Commons.makeRandomHash();
        }
        userTransaction.setHash(unique_hash);

        userTransaction.setTransaction_id(transaction_id);
        userTransaction.setUser_from(senderUser.getId());
        userTransaction.setUser_to(receiverUser.getId());
        userTransaction.setAmount(amount);
        userTransaction.setRemarks(remarks);
        userTransaction.setTransaction_type(TransactionType.USER_TO_USER_TRANSACTION);
        Commons.createEntity(userTransaction);

        userTransaction = userTransactionRepository.save(userTransaction);
        if(userTransaction != null) {
            return new ReturnContainer(userTransaction);
        }
        
        return new ReturnContainer(StandardErrors.E2001_TRANSACTION_FAILED);
    }

    public ReturnContainer<UserTransaction> makeNewServiceChargeTransaction(StandardUser senderUser, Double amount, ServiceCharge serviceCharge) {
        UserTransaction userTransaction = new UserTransaction();

        // Generate Unique Transaction ID
        String transaction_id = Commons.getRandomAlphaNumeric(12);
        while (userTransactionRepository.findUserTransactionByTransactionId(transaction_id) != null) {
            transaction_id = Commons.getRandomAlphaNumeric(12);
        }
        
        // Generate Unique Hash
        String unique_hash = Commons.makeRandomHash();
        while (userTransactionRepository.findUserTransactionByHash(unique_hash) != null) {
            unique_hash = Commons.makeRandomHash();
        }
        userTransaction.setHash(unique_hash);

        userTransaction.setTransaction_id(transaction_id);
        userTransaction.setUser_from(senderUser.getId());
        userTransaction.setUser_to(standardUserService.getSystemUser().getData().getId());
        userTransaction.setAmount(amount);
        userTransaction.setService_charge(serviceCharge);
        userTransaction.setTransaction_type(TransactionType.USER_TO_SERVICE_CHARGE);
        Commons.createEntity(userTransaction);

        userTransaction = userTransactionRepository.save(userTransaction);
        if(userTransaction != null) {
            return new ReturnContainer(userTransaction);
        }
        
        return new ReturnContainer(StandardErrors.E2001_TRANSACTION_FAILED);
    }

}
