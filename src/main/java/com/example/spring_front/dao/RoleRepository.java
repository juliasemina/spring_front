package com.example.spring_front.dao;

import com.example.spring_front.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    @Query("SELECT u FROM Role u WHERE u.name=?1")
//    Role getRoleByName(String roleName);

    Role getRoleByName(String roleName);

}