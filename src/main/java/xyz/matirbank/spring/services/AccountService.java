package xyz.matirbank.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.matirbank.spring.models.Enums.AccountType;
import xyz.matirbank.spring.models.ReturnContainer;
import xyz.matirbank.spring.models.entities.Account;
import xyz.matirbank.spring.repositories.AccountRepository;
import xyz.matirbank.spring.utils.Commons;
import xyz.matirbank.spring.utils.StandardErrors;

public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StandardUserService standardUserService;

    ReturnContainer<Account> createUserWalletAccount(Long user_id) {
        Account account = new Account();
        account.setUser_id(user_id);
        account.setBalance(0D);
        account.setAccount_type(AccountType.WALLET);
        Commons.createEntity(account);

        // Check and Return Duplicate Account
        if (accountRepository.findAccountByUserAndType(user_id, AccountType.WALLET) != null) {
            return new ReturnContainer(StandardErrors.E3001_USER_WALLET_ACCOUNT_ALREADY_EXISTS);
        }

        String hash = Commons.makeRandomHash();
        while (accountRepository.findAccountByHash(hash) != null) {
            hash = Commons.makeRandomHash();
        }

        account = accountRepository.save(account);
        return new ReturnContainer(account);
    }

    ReturnContainer<Account> createSystemAccount(String accountHeadName) {
        Long user_id = standardUserService.getSystemUser().getData().getId();

        // Check and Return Duplicate Account
        if (accountRepository.findAccountByUserAndHead(user_id, accountHeadName) != null) {
            return new ReturnContainer(StandardErrors.E3002_SYSTEM_ACCOUNT_ALREADY_EXISTS);
        }

        Account account = new Account();
        account.setUser_id(user_id);
        account.setBalance(0D);
        account.setAccount_type(AccountType.SYSTEM);
        account.setAccount_head(accountHeadName);

        String hash = Commons.makeRandomHash();
        while (accountRepository.findAccountByHash(hash) != null) {
            hash = Commons.makeRandomHash();
        }

        account = accountRepository.save(account);
        return new ReturnContainer(account);
    }
}
