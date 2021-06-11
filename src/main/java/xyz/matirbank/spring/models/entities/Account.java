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
public class Account extends BaseEntity implements Serializable {
    
    long user_id;
    AccountType account_type;
    Double balance;
    Date balance_updated;

    public Account() {}

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public AccountType getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountType account_type) {
        this.account_type = account_type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getBalance_updated() {
        return balance_updated;
    }

    public void setBalance_updated(Date balance_updated) {
        this.balance_updated = balance_updated;
    }
    
    
}
