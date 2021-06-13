package xyz.matirbank.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.models.Enums.AccountType;
import xyz.matirbank.spring.models.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, QueryByExampleExecutor<Account> {

    @Query(value = "SELECT * FROM `account` WHERE `user_id` = ?1 AND `account_type` = ?2 LIMIT 0, 1", nativeQuery = true)
    Account findAccountByUserAndType(Long user_id, AccountType account_type);

    @Query(value = "SELECT * FROM `account` WHERE `user_id` = ?1 AND `account_head` = ?2 LIMIT 0, 1", nativeQuery = true)
    Account findAccountByUserAndHead(Long user_id, String account_head);

    @Query(value = "SELECT * FROM `account` WHERE `hash` = ?1 LIMIT 0, 1", nativeQuery = true)
    Account findAccountByHash(String hash);

}
