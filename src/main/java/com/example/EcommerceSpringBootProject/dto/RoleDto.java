package com.example.EcommerceSpringBootProject.dto;

import javax.validation.constraints.NotNull;

public class RoleDto{
    private Long id;
    @NotNull
    private String roleName;

    public RoleDto() {}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
