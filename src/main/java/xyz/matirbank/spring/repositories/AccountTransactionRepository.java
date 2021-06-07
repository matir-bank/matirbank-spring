package xyz.matirbank.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.models.entities.AccountTransactions;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactions, Long>, QueryByExampleExecutor<AccountTransactions> {
    
}
