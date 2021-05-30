package xyz.matirbank.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QueryByExampleExecutor<User> {
    
    @Query(value = "SELECT * FROM `user` WHERE `phone` = ?1 LIMIT 0, 1", nativeQuery = true)
    User findUsersByPhone(String phone);
    
}