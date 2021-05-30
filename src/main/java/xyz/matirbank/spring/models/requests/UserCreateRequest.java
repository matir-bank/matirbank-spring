package xyz.matirbank.spring.models.requests;

import xyz.matirbank.spring.models.Enums.AccountType;

public class UserCreateRequest {
    
    String name;
    String phone;
    String password;
    AccountType account_type;
    
    public UserCreateRequest(){}

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
