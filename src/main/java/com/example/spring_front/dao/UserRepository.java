package com.example.spring_front.dao;

import com.example.spring_front.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.name=?1")
//    User findByUserName(String username);

    User findUserByName(String username);
}