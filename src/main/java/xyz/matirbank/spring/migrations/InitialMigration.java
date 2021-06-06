package xyz.matirbank.spring.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.matirbank.spring.models.Enums;
import xyz.matirbank.spring.models.Enums.UserType;
import xyz.matirbank.spring.models.entities.StandardUsers;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.services.StandardUserService;

public class InitialMigration {
    
    @Autowired
    StandardUserService standardUserService;
    
    public InitialMigration() {}
    
    public void migrate() {
        createSystemStandardUsers();
        createSystemStandardUsersAccounts();
        depositInitialFunds();
    }
    
    private void createSystemStandardUsers() {
        standardUserService.createUser(new StandardUserSignupRequest("MatirBank", "0000000001", "", UserType.SYSTEM));
        StandardUsers mainUser = standardUserService.getUserByPhone("0000000001");
    }
    
    private void createSystemStandardUsersAccounts() {
        
    }
    
    private void depositInitialFunds() {
        
    }
    
}
