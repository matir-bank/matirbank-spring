package xyz.matirbank.spring.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.matirbank.spring.models.Enums.UserType;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.services.StandardUserService;

public class InitialMigration {

    @Autowired
    private static StandardUserService standardUserService;

    public static void migrate() {
        createSystemStandardUsers();
        createSystemStandardUsersAccounts();
        depositInitialFunds();
    }

    private static void createSystemStandardUsers() {
        //standardUserService.createUser(new StandardUserSignupRequest("MatirBank", "0000000000", "", UserType.SYSTEM));
    }

    private static void createSystemStandardUsersAccounts() {

    }

    private static void depositInitialFunds() {

    }

}
