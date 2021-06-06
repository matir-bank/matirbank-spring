package xyz.matirbank.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.models.entities.StandardUsers;

@Repository
public interface StandardUserRepository extends JpaRepository<StandardUsers, Long>, QueryByExampleExecutor<StandardUsers> {
    
    @Query(value = "SELECT * FROM `user` WHERE `phone` = ?1 AND `password_hashed` = ?2 LIMIT 0, 1", nativeQuery = true)
    StandardUsers loginUser(String phone, String password_hashed);
    
    @Query(value = "SELECT * FROM `user` WHERE `phone` = ?1 LIMIT 0, 1", nativeQuery = true)
    StandardUsers findUserByPhone(String phone);
    
    @Query(value = "SELECT * FROM `user` WHERE `hash` = ?1 LIMIT 0, 1", nativeQuery = true)
    StandardUsers findUserByHash(String hash);
    
}