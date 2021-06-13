package xyz.matirbank.spring.models.requests;

public class StandardUserLoginRequest {

    String phone;
    String password;

    public StandardUserLoginRequest() {
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

}
