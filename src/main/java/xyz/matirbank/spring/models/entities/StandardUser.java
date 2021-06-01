package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.userdetails.UserDetails;

import xyz.matirbank.spring.models.Enums.AccountType;

@Entity
@Table(name = "user", uniqueConstraints=@UniqueConstraint(columnNames={"phone","hash"}))
@JsonInclude(Include.NON_NULL)
public class StandardUser extends BaseEntity implements Serializable {
    
    String name;
    
    @Column(unique=true)
    String phone;
    
    @JsonIgnore
    String password_hashed;
    
    AccountType account_type;
    
    Double balance;
    
    @JsonIgnore
    Date balance_updated;
    
    @OneToOne(targetEntity=Photos.class)
    @JoinColumn(name = "user_id")
    Photos profile_photo;
    
    @OneToMany(
        targetEntity=IdentityDocuments.class,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "user_id")
    List<IdentityDocuments> identity_documents;
    
    public StandardUser() {}
    
    public UserDetails toUserDetails() {
        if(!getHash().equals("") && !getPassword_hashed().equals("")) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(getHash(), getPassword_hashed(), new ArrayList<>());
            return userDetails;
        }
        return null;
    }
    
    public StandardUser toScopedData() {
        StandardUser standardUser = new StandardUser();
        standardUser.setHash(getHash());
        standardUser.setName(getName());
        standardUser.setProfile_photo(getProfile_photo());
        standardUser.setAccount_type(getAccount_type());
        return standardUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword_hashed() {
        return password_hashed;
    }

    public void setPassword_hashed(String password_hashed) {
        this.password_hashed = password_hashed;
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

    public Photos getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(Photos profile_photo) {
        this.profile_photo = profile_photo;
    }

    public List<IdentityDocuments> getIdentity_documents() {
        if(identity_documents != null){
            if(identity_documents.isEmpty()){
                return null;
            }
        }
        return identity_documents;
    }

    public void setIdentity_documents(List<IdentityDocuments> identity_documents) {
        this.identity_documents = identity_documents;
    }
    
    
}
