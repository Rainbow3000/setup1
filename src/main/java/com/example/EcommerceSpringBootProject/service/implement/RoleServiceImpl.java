package com.example.EcommerceSpringBootProject.service.implement;

import com.example.EcommerceSpringBootProject.entity.Role;
import com.example.EcommerceSpringBootProject.exception.DuplicateException;
import com.example.EcommerceSpringBootProject.repository.RoleRepository;
import com.example.EcommerceSpringBootProject.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role save(Role role) {
        Role roleExists =  roleRepository.findByRoleName(role.getRoleName()).orElse(null);
        if(roleExists != null){
           throw  new  DuplicateException("Role already exists !");
        }
        return roleRepository.save(role) ;
    }

}
