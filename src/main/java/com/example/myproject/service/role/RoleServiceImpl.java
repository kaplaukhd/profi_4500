package com.example.myproject.service.role;

import com.example.myproject.entity.Role;
import com.example.myproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveAll(Collection<Role> roles) {
        roleRepository.saveAll(roles);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
