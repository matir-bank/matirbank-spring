package xyz.matirbank.spring.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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

    public StandardUser createUser(StandardUserSignupRequest userRequest) {
        StandardUser user = new StandardUser();
        
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setUser_type(userRequest.getUser_type());
        user.setBalance(0.00d);
        
        String hashed_password = Commons.encodePassword(userRequest.getPassword());
        user.setPassword_hashed(hashed_password);

        Date date = new Date();
        user.setDate_created(date);
        user.setDate_updated(date);
        user.setBalance_updated(date);

        StandardUser savedUser = userRepository.save(user);
        String user_hash = Commons.makeIdHash(savedUser.getId());
        
        while(userRepository.findUserByHash(user_hash) != null) {
            user_hash = Commons.makeIdHash(savedUser.getId());
        }
        
        savedUser.setHash(user_hash);
        savedUser = userRepository.save(user);

        return savedUser;
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
