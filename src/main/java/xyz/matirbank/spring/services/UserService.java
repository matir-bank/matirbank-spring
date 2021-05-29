package xyz.matirbank.spring.services;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.entities.User;
import xyz.matirbank.spring.entities.UserRequest;
import xyz.matirbank.spring.repositories.UserRepository;
import xyz.matirbank.spring.utils.Commons;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    public User createUser(UserRequest userRequest) {
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

        savedUser.setPassword_hashed(null);
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
        User user = new User();
        user.setPhone(phone);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("phone", match -> match.startsWith());

        Example<User> exampleUser = Example.of(user, matcher);

        Optional<User> userResponse = userRepository.findOne(exampleUser);

        if (userResponse.isPresent()) {
            return userResponse.get();
        } else {
            return null;
        }
    }
}
