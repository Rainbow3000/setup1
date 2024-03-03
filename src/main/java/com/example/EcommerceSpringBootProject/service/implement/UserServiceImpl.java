package com.example.EcommerceSpringBootProject.service.implement;


import com.example.EcommerceSpringBootProject.entity.Role;
import com.example.EcommerceSpringBootProject.entity.User;
import com.example.EcommerceSpringBootProject.exception.DuplicateException;
import com.example.EcommerceSpringBootProject.exception.NotFoundException;
import com.example.EcommerceSpringBootProject.repository.RoleRepository;
import com.example.EcommerceSpringBootProject.repository.UserRepository;
import com.example.EcommerceSpringBootProject.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User save(User user) {
        User userExist = userRepository.findByEmail(user.getEmail());
        if(userExist != null){
            throw new DuplicateException("Tài khoản email này đã tồn tại.");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        Role roleUser = roleRepository.findByRoleName("ROLE_USER").orElse(null);
        if(roleUser == null){
            user.addRole(roleRepository.save(new Role("ROLE_USER")));
            user.setPassword(password);
            return  userRepository.save(user);
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        user.setStatus(1);
        user.setPassword(password);
        user.addRole(roleUser);
        user.setCreatedDate(date);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public Boolean updateByAdmin(User userRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("Not found user id"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setPassword(password);
        userRequest.getRoles().forEach(roleItem->{
            Role role = new Role();
            role.setRoleName(roleItem.getRoleName());
            user.addRole(role);
        });
        user.setStatus(1);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean updateByUser(User user, Long userId) {
        return null;
    }

    @Override
    public Boolean delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("Not found user id"));
        user.setStatus(2);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
