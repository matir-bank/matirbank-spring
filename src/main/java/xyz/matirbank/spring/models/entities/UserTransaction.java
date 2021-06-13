package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import xyz.matirbank.spring.models.Enums;
import xyz.matirbank.spring.models.Enums.ServiceCharge;
import xyz.matirbank.spring.models.Enums.TransactionType;

@Entity
@Table(name = "user_transactions", uniqueConstraints = @UniqueConstraint(columnNames = {"hash"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTransaction extends BaseEntity implements Serializable {

    String transaction_id;
    Long user_from;
    Long user_to;
    Long account_transaction_id;
    Double amount;
    TransactionType transaction_type;
    ServiceCharge service_charge;
    Long transaction_reference_id;
    String remarks;

    public UserTransaction() {
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Long getUser_from() {
        return user_from;
    }

    public void setUser_from(Long user_from) {
        this.user_from = user_from;
    }

    public Long getUser_to() {
        return user_to;
    }

    public void setUser_to(Long user_to) {
        this.user_to = user_to;
    }

    public Long getAccount_transaction_id() {
        return account_transaction_id;
    }

    public void setAccount_transaction_id(Long account_transaction_id) {
        this.account_transaction_id = account_transaction_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(TransactionType transaction_type) {
        this.transaction_type = transaction_type;
    }

    public ServiceCharge getService_charge() {
        return service_charge;
    }

    public void setService_charge(ServiceCharge service_charge) {
        this.service_charge = service_charge;
    }

    public Long getTransaction_reference_id() {
        return transaction_reference_id;
    }

    public void setTransaction_reference_id(Long transaction_reference_id) {
        this.transaction_reference_id = transaction_reference_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
