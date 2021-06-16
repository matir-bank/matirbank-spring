package xyz.matirbank.spring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.models.entities.UserTransaction;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long>, QueryByExampleExecutor<UserTransaction> {

    @Query(value = "SELECT * FROM `user_transactions` WHERE `user_from` = ?1 OR `user_to` = ?1 ORDER BY `id` DESC", nativeQuery = true)
    List<UserTransaction> getUserTransactions(Long user_id);

    @Query(value = "SELECT * FROM `user_transactions` WHERE `transaction_id` = ?1 LIMIT 0, 1", nativeQuery = true)
    UserTransaction findUserTransactionByTransactionId(String transaction_id);

    @Query(value = "SELECT * FROM `user_transactions` WHERE `hash` = ?1 LIMIT 0, 1", nativeQuery = true)
    UserTransaction findUserTransactionByHash(String hash);
    
}
