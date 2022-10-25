package com.moussa.gestionstock.dto;

import com.moussa.gestionstock.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private Integer id;
    private String roleName;

    public static RoleDto fromEntity(Role role){
        if (role == null){
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        if (roleDto == null){
            return null;
        }
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }
}
