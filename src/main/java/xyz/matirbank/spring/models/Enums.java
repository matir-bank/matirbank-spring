package xyz.matirbank.spring.models;

public class Enums {
    
    public enum AccountType {
        WALLET, SAVINGS, DEPOSIT, LOAN, SYSTEM
    }
    
    public enum UserType {
        WALLET, AGENT, MERCHANT, SYSTEM;
    }
    
    public enum IdentityType {
        NATIONAL_ID, DRIVING_LICENSE, PASSPORT, BIRTH_CERTIFICATE
    }
    
}
