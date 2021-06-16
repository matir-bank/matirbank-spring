package xyz.matirbank.spring.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.matirbank.spring.models.Enums.UserType;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.services.StandardUserService;

@Component
public class InitialMigration {

    @Autowired
    StandardUserService standardUserService;
    
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        migrate();
    }

    public void migrate() {
        createSystemStandardUsers();
        createSystemStandardUsersAccounts();
        depositInitialFunds();
    }

    private void createSystemStandardUsers() {
        standardUserService.createUser(new StandardUserSignupRequest("MatirBank", "0000000000", "", UserType.ROOT));
    }

    private void createSystemStandardUsersAccounts() {

    }

    private void depositInitialFunds() {

    }

}
