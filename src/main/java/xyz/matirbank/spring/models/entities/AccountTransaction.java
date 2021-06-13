package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "account_transactions", uniqueConstraints = @UniqueConstraint(columnNames = {"hash"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountTransaction extends BaseEntity implements Serializable {

    Long account_from;
    Long account_to;
    Double amount;

    public AccountTransaction() {
    }

    public Long getAccount_from() {
        return account_from;
    }

    public void setAccount_from(Long account_from) {
        this.account_from = account_from;
    }

    public Long getAccount_to() {
        return account_to;
    }

    public void setAccount_to(Long account_to) {
        this.account_to = account_to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
