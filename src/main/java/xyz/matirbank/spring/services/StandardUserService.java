package xyz.matirbank.spring.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.models.ReturnContainer;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.models.requests.StandardUserLoginRequest;
import xyz.matirbank.spring.utils.Commons;
import xyz.matirbank.spring.repositories.StandardUserRepository;

@Service
public class StandardUserService {

    @Autowired
    StandardUserRepository userRepository;

    public StandardUserService() {}
    
    public StandardUser loginUser(StandardUserLoginRequest userLoginRequest) {
        String hashed_password = Commons.encodePassword(userLoginRequest.getPassword());
        StandardUser user = userRepository.loginUser(userLoginRequest.getPhone(), hashed_password);
        return user;
    }

    public ReturnContainer<StandardUser> createUser(StandardUserSignupRequest userRequest) {
        StandardUser user = new StandardUser();
        
        // Basic User Profile
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setUser_type(userRequest.getUser_type());
        user.setBalance(0.00d);
        user.setBalance_updated(new Date());
        Commons.createEntity(user);
        
        if(userRepository.findUserByPhone(userRequest.getPhone()) != null){
            return new ReturnContainer<StandardUser>().returnErrorSummary(1001, "Phone Number Already Exists");
        }
        
        // Password
        String hashed_password = Commons.encodePassword(userRequest.getPassword());
        user.setPassword_hashed(hashed_password);

        // Generate User Hash
        String user_hash = Commons.makeRandomHash();
        while(userRepository.findUserByHash(user_hash) != null) {
            user_hash = Commons.makeRandomHash();
        }
        
        // Save User
        user = userRepository.save(user);
        
        return new ReturnContainer<StandardUser>().returnData(user);
    }

    public StandardUser getUserById(long id) {
        StandardUser user = userRepository.getById(id);

        StandardUser subUser = new StandardUser();
        subUser.setId(user.getId());
        subUser.setName(user.getName());
        subUser.setProfile_photo(user.getProfile_photo());

        return subUser;
    }

    public StandardUser getUserByPhone(String phone) {
        return userRepository.findUserByPhone(phone);
    }
    
    public StandardUser getUserByHash(String hash) {
        return userRepository.findUserByHash(hash);
    }
    
    public String getCurrentUserHash() {
        String hash = "";
        try {
            hash = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) { }
        
        return hash;
    }
    
    public long getCurrentUserId() {
        String hash = getCurrentUserHash();
        if(!hash.isEmpty()) {
            StandardUser user = userRepository.findUserByHash(hash);
            return user.getId();
        }else{
            return -1;
        }
    }
    
    public StandardUser getCurrentUser() {
        String hash = getCurrentUserHash();
        if(!hash.isEmpty()) {
            StandardUser user = userRepository.findUserByHash(hash);
            return user;
        }
        return null;
    }
    
    public StandardUser updateUser(StandardUser user) {
        return userRepository.save(user);
    }
    
    public StandardUser getSystemUser() {
        return userRepository.findSystemUser();
    }
    
}
