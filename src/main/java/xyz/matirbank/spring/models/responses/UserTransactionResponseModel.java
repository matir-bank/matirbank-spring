package xyz.matirbank.spring.models.responses;

import java.util.Date;
import xyz.matirbank.spring.models.entities.StandardUser;

public class UserTransactionResponseModel {
    
    String transaction_id;
    StandardUser user_from;
    StandardUser user_to;
    Double amount;
    Date transaction_date;
    
}
