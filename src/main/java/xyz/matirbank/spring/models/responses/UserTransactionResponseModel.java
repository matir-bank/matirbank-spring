package xyz.matirbank.spring.models.responses;

import java.util.Date;
import xyz.matirbank.spring.models.entities.StandardUsers;

public class UserTransactionResponseModel {
    
    String transaction_id;
    StandardUsers user_from;
    StandardUsers user_to;
    Double amount;
    Date transaction_date;
    
}
