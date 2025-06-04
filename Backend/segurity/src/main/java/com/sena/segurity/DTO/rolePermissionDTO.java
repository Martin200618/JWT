package com.sena.segurity.DTO;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class rolePermissionDTO {
    private int rolePermissionId;
    private roleDTO role;
    private pageDTO page;
    private LocalDateTime createRolePermission;
    private LocalDateTime updateRolePermission;
}