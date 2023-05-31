package com.example.myproject.repository;

import com.example.myproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);

    Role findRoleById(Long id);
}
