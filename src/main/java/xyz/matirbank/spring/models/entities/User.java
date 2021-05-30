package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import xyz.matirbank.spring.models.Enums.AccountType;

@Entity
@Table(name = "user")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    String name;
    String phone;
    
    String hash;
    
    @JsonIgnore
    String password_hashed;
    
    AccountType account_type;
    Double balance;
    
    @OneToOne(targetEntity=Photo.class)
    Photo photo;
    
    @OneToMany(
        targetEntity=Identity.class,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "user_id")
    List<Identity> identities = new ArrayList<>();
    
    Date balance_updated;
    Date date_created;
    Date date_updated;
    
    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }

    public Date getBalance_updated() {
        return balance_updated;
    }

    public void setBalance_updated(Date balance_updated) {
        this.balance_updated = balance_updated;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    
}
