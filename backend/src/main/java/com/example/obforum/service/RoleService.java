package com.example.obforum.service;


import com.example.obforum.model.Role;

public interface RoleService {
    Role findByName(String name);
}
