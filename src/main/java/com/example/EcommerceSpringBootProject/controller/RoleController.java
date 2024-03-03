package com.example.EcommerceSpringBootProject.controller;

import com.example.EcommerceSpringBootProject.constants.Message;
import com.example.EcommerceSpringBootProject.dto.RoleDto;
import com.example.EcommerceSpringBootProject.entity.Role;
import com.example.EcommerceSpringBootProject.exception.DuplicateException;
import com.example.EcommerceSpringBootProject.response.DataResponse;
import com.example.EcommerceSpringBootProject.service.interfaces.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> create(@RequestBody @Valid RoleDto roleDto){
        try{
            Role roleRequest =  modelMapper.map(roleDto,Role.class);
            Role role = iRoleService.save(roleRequest);
            RoleDto roleResponse = modelMapper.map(role,RoleDto.class);
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(), Message.success,roleResponse,null));
        }catch (DuplicateException ex){
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.FORBIDDEN.value(), Message.failure,null,ex.getMessage()));
        }
    }

}
