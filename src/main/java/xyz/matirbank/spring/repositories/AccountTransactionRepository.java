package xyz.matirbank.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.models.entities.AccountTransaction;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long>, QueryByExampleExecutor<AccountTransaction> {

    @Query(value = "SELECT * FROM `account_transactions` WHERE `hash` = ?1 LIMIT 0, 1", nativeQuery = true)
    AccountTransaction findAccountTransactionByHash(String hash);
    
}
