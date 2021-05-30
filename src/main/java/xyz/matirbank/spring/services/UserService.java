package xyz.matirbank.spring.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.models.entities.User;
import xyz.matirbank.spring.models.requests.UserCreateRequest;
import xyz.matirbank.spring.repositories.UserRepository;
import xyz.matirbank.spring.utils.Commons;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {}
    
    public User loginUser(String username, String password) {
        String hashed_password = Commons.encodePassword(password);
        User user = userRepository.loginUser(username, hashed_password);
        return user;
    }

    public User createUser(UserCreateRequest userRequest) {
        User user = new User();
        
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setAccount_type(userRequest.getAccount_type());
        user.setBalance(0.00d);
        
        String hashed_password = Commons.encodePassword(userRequest.getPassword());
        user.setPassword_hashed(hashed_password);

        Date date = new Date();
        user.setDate_created(date);
        user.setDate_updated(date);
        user.setBalance_updated(date);

        User savedUser = userRepository.save(user);
        String user_hash = Commons.makeIdHash(savedUser.getId());
        savedUser.setHash(user_hash);
        savedUser = userRepository.save(user);

        return savedUser;
    }

    public User getUserById(long id) {
        User user = userRepository.getById(id);

        User subUser = new User();
        subUser.setId(user.getId());
        subUser.setName(user.getName());
        subUser.setPhoto(user.getPhoto());

        return subUser;
    }

    public User getUserByPhone(String phone) {
        return userRepository.findUserByPhone(phone);
    }
    
    public User getUserByHash(String hash) {
        return userRepository.findUserByHash(hash);
    }
    
}
