package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "account_transactions", uniqueConstraints=@UniqueConstraint(columnNames={"hash"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountTransactions extends BaseEntity implements Serializable {
    
    Long account_from;
    Long account_to;
    Double amount;
    
}
