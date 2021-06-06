package xyz.matirbank.spring.models.requests;

import xyz.matirbank.spring.models.Enums.UserType;

public class StandardUserSignupRequest {
    
    String name;
    String phone;
    String password;
    UserType user_type;
    
    public StandardUserSignupRequest(){}

    public StandardUserSignupRequest(String name, String phone, String password, UserType user_type) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.user_type = user_type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUser_type() {
        return user_type;
    }

    public void setUser_type(UserType user_type) {
        this.user_type = user_type;
    }

}
