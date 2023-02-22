package com.example.sprint.service;

import com.example.sprint.model.Role;
import com.example.sprint.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
