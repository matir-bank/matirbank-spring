package xyz.matirbank.spring.entities;

import xyz.matirbank.spring.entities.Enums.AccountType;

public class UserRequest {
    String name;
    String phone;
    String password;
    AccountType account_type;
    
    public UserRequest(){}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountType account_type) {
        this.account_type = account_type;
    }
    
    
}
