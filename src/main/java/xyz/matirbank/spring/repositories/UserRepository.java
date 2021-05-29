package xyz.matirbank.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.matirbank.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}