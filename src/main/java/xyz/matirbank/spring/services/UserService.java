package xyz.matirbank.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.entities.User;
import xyz.matirbank.spring.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    User createUser(String name, String phone, String password) {
       User user = new User();
       user.setName(name);
       user.setPhone(phone);
       user.setPassword_hashed(password);
       User savedUser = userRepository.save(user);
       savedUser.setPassword_hashed(null);
       return savedUser;
    }
    
    User getUserById(long id) {
        User user = userRepository.getById(id);
        
        User subUser = new User();
        subUser.setId(user.getId());
        subUser.setName(user.getName());
        subUser.setPhoto(user.getPhoto());
        
        return subUser;
    }
}
