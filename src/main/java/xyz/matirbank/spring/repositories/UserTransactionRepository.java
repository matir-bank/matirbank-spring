package xyz.matirbank.spring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.models.entities.UserTransactions;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransactions, Long>, QueryByExampleExecutor<UserTransactions>  {
    
    @Query(value = "SELECT * FROM `user_transactions` WHERE `user_from` = ?1 OR `user_to` = ?1 ORDER BY `id` DESC", nativeQuery = true)
    List<UserTransactions> getUserTransactions(Long user_id);
    
    
}