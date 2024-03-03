package com.example.EcommerceSpringBootProject.database;

import com.example.EcommerceSpringBootProject.repository.RoleRepository;
import com.example.EcommerceSpringBootProject.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabaseInit implements CommandLineRunner {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {

    }
}
