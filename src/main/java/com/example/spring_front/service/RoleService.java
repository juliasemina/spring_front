package com.example.spring_front.service;


import com.example.spring_front.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role getRoleByName(String rolename);

}
