package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import xyz.matirbank.spring.models.Enums.AccountType;

@Entity
@Table(name = "accounts", uniqueConstraints=@UniqueConstraint(columnNames={"hash"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accounts extends BaseEntity implements Serializable {
    
    long user_id;
    AccountType account_type;
    Double balance;
    Date balance_updated;
    
}
