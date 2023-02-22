package com.example.sprint.service.impl;

import com.example.sprint.model.Role;
import com.example.sprint.model.RoleName;
import com.example.sprint.repository.IRoleRepository;
import com.example.sprint.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
