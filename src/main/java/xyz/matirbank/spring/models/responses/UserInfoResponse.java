package xyz.matirbank.spring.models.responses;

import xyz.matirbank.spring.models.Enums.AccountType;
import xyz.matirbank.spring.models.entities.Photo;
import xyz.matirbank.spring.models.entities.User;

public class UserInfoResponse {
    
    String hash;
    String name;
    Photo photo;
    AccountType account_type;
    
    public static UserInfoResponse fromUser(User user) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setName(user.getName());
        userInfoResponse.setPhoto(user.getPhoto());
        userInfoResponse.setAccount_type(user.getAccount_type());
        userInfoResponse.setHash(user.getHash());
        return userInfoResponse;
    }

    public UserInfoResponse() {}
    
    public UserInfoResponse(String hash, String name, Photo photo, AccountType account_type) {
        this.hash = hash;
        this.name = name;
        this.photo = photo;
        this.account_type = account_type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public AccountType getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountType account_type) {
        this.account_type = account_type;
    }

    
}
