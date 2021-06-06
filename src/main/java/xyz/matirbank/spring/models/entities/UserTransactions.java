package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_transactions", uniqueConstraints=@UniqueConstraint(columnNames={"hash"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTransactions extends BaseEntity implements Serializable {
    
    String transaction_id;
    Long user_from;
    Long user_to;
    Long account_transaction_id;
    Double amount;
    
}
