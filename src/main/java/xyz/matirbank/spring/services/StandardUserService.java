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
import xyz.matirbank.spring.utils.StandardErrors;

@Service
public class StandardUserService {

    @Autowired
    StandardUserRepository userRepository;

    public StandardUserService() {
    }

    public ReturnContainer<StandardUser> loginUser(StandardUserLoginRequest userLoginRequest) {
        String hashed_password = Commons.encodePassword(userLoginRequest.getPassword());
        StandardUser user = userRepository.loginUser(userLoginRequest.getPhone(), hashed_password);
        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user);
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1004_INCORRECT_LOGIN_DETAILS);
        }
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

        if (userRepository.findUserByPhone(userRequest.getPhone()) != null) {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1002_PHONE_NUMBER_ALREADY_EXISTS);
        }

        // Password
        String hashed_password = Commons.encodePassword(userRequest.getPassword());
        user.setPassword_hashed(hashed_password);

        // Generate User Hash
        String user_hash = Commons.makeRandomHash();
        while (userRepository.findUserByHash(user_hash) != null) {
            user_hash = Commons.makeRandomHash();
        }
        user.setHash(user_hash);

        // Save User
        user = userRepository.save(user);

        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user);
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1005_USER_ACCOUNT_UPDATE_FAILED);
        }
    }

    public ReturnContainer<StandardUser> getUserById(long id) {
        StandardUser user = userRepository.getById(id);
        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user.toScopedData());
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1003_USER_ACCOUNT_NOT_FOUND);
        }
    }

    public ReturnContainer<StandardUser> getUserByPhone(String phone) {
        StandardUser user = userRepository.findUserByPhone(phone);
        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user.toScopedData());
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1003_USER_ACCOUNT_NOT_FOUND);
        }
    }

    public ReturnContainer<StandardUser> getUserByHash(String hash) {
        StandardUser user = userRepository.findUserByHash(hash);

        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user.toScopedData());
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1003_USER_ACCOUNT_NOT_FOUND);
        }
    }

    public ReturnContainer<String> getCurrentUserHash() {
        String hash = "";
        try {
            hash = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
        }

        if (!hash.equals("")) {
            return new ReturnContainer<String>().returnData(hash);
        } else {
            return new ReturnContainer<String>().returnErrorSummary(StandardErrors.E1007_USER_AUTHENTICATION_ERROR);
        }
    }

    public ReturnContainer<Long> getCurrentUserId() {
        ReturnContainer<String> hashContainer = getCurrentUserHash();
        if (hashContainer.getStatus()) {
            StandardUser user = userRepository.findUserByHash(hashContainer.getData());
            if (user != null) {
                return new ReturnContainer<Long>().returnData(user.getId());
            } else {
                return new ReturnContainer<Long>().returnErrorSummary(StandardErrors.E1003_USER_ACCOUNT_NOT_FOUND);
            }
        } else {
            return new ReturnContainer<Long>().returnErrorSummary(StandardErrors.E1007_USER_AUTHENTICATION_ERROR);
        }
    }

    public ReturnContainer<StandardUser> getCurrentUser() {
        ReturnContainer<String> hashContainer = getCurrentUserHash();
        if (hashContainer.getStatus()) {
            StandardUser user = userRepository.findUserByHash(hashContainer.getData());
            if (user != null) {
                return new ReturnContainer<StandardUser>().returnData(user);
            } else {
                return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1003_USER_ACCOUNT_NOT_FOUND);
            }
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1007_USER_AUTHENTICATION_ERROR);
        }
    }

    public ReturnContainer<StandardUser> updateUser(StandardUser userData) {
        StandardUser user = userRepository.save(userData);
        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user);
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1005_USER_ACCOUNT_UPDATE_FAILED);
        }
    }

    public ReturnContainer<StandardUser> getSystemUser() {
        StandardUser user = userRepository.findSystemUser();
        if (user != null) {
            return new ReturnContainer<StandardUser>().returnData(user);
        } else {
            return new ReturnContainer<StandardUser>().returnErrorSummary(StandardErrors.E1006_SYSTEM_USER_ACCOUNT_NOT_FOUND);
        }
    }
}
