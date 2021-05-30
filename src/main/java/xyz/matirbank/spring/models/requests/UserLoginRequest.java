package xyz.matirbank.spring.models.requests;

public class UserLoginRequest {
    
    String phone;
    String password;
    
    public UserLoginRequest(){}

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
    
    
    
}
