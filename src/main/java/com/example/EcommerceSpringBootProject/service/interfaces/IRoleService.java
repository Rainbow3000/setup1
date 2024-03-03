package com.example.EcommerceSpringBootProject.service.interfaces;

import com.example.EcommerceSpringBootProject.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService {
    Role save(Role role);
}
