package com.example.spring_front.service;

import com.example.spring_front.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();
    public User save(User user);
    public User getUserbyId(Long id);
    public void delete(Long id);
    public  User findByUsername(String username);
}
