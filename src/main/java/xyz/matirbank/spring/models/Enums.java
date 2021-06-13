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

    public enum TransactionType {
        SYSTEM_DEPOSIT, SYSTEM_TO_SYSTEM_TRANSACTION,
        USER_TO_USER_TRANSACTION, USER_TO_SYSTEM_TRANSACTION, USER_TO_SERVICE_CHARGE,
        USER_CARD_DEPOSIT, USER_BANK_DEPOSIT, USER_AGENT_DEPOSIT,
        USER_TO_AGENT_WITHDRAW, USER_TO_BANK_WITHDRAW,
        SYSTEM_TO_AGENT_TRANSACTION, AGENT_TO_SYSTEM_TRANSACTION
    }

    public enum ServiceCharge {
        USER_SEND_MONEY, AGENT_CASHOUT
    }
}
