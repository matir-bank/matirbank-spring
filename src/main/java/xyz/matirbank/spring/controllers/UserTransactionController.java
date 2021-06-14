package xyz.matirbank.spring.controllers;

import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.matirbank.spring.models.Enums.ServiceCharge;
import xyz.matirbank.spring.models.ReturnContainer;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.entities.UserTransaction;
import xyz.matirbank.spring.models.requests.SendMoneyRequest;
import xyz.matirbank.spring.models.responses.base.BaseResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;
import xyz.matirbank.spring.services.StandardUserService;
import xyz.matirbank.spring.services.UserTransactionService;

@RestController
@RequestMapping("/api/transaction")
public class UserTransactionController {

    @Autowired
    StandardUserService standardUserService;

    @Autowired
    UserTransactionService userTransactionsService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/send-money")
    public ResponseEntity<BaseResponse<List<UserTransaction>>> sendMoney(SendMoneyRequest sendMoneyRequest) {
        StandardUser senderUser = standardUserService.getCurrentUser().getData();
        StandardUser receiverUser = standardUserService.getUserByHash(sendMoneyRequest.getUser_hash()).getData();

        UserTransaction userMainTransaction = userTransactionsService
                .makeNewUserTransaction(
                        senderUser,
                        receiverUser,
                        sendMoneyRequest.getAmount(),
                        sendMoneyRequest.getRemarks()
                );

        UserTransaction userServiceChargeTransaction = userTransactionsService.
                makeNewServiceChargeTransaction(
                        senderUser,
                        2.5D,
                        ServiceCharge.USER_SEND_MONEY
                );

        List<UserTransaction> userTransactions = new ArrayList<>();
        userTransactions.add(userMainTransaction);
        userTransactions.add(userServiceChargeTransaction);

        return new BaseResponseEntity<>().basicData(userTransactions).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<UserTransaction>>> getTransactions() {
        ReturnContainer<List<UserTransaction>> userTransactions = userTransactionsService.getUserTransactions(standardUserService.getCurrentUser().getData().getId());
        return new BaseResponseEntity<>().basicData(userTransactions).getEntity();
    }

}
