package com.example.myproject.service.role;

import com.example.myproject.entity.Role;

import java.util.Collection;

public interface RoleService {

    void saveRole(Role role);
    void saveAll(Collection<Role> roles);
    void deleteRole(Role role);
    void deleteRoleById(Long id);
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
