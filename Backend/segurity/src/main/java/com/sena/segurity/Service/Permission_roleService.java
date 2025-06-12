package com.sena.segurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.segurity.DTO.Permission_roleDTO;
import com.sena.segurity.DTO.ResponsesDTO;
import com.sena.segurity.Model.pages;
import com.sena.segurity.Model.permissionRoles;
import com.sena.segurity.Model.roles;
import com.sena.segurity.Repository.IPage;
import com.sena.segurity.Repository.Ipermission_role;
import com.sena.segurity.Repository.Irole;

import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Service
public class Permission_roleService {
    @Autowired
    private Ipermission_role data;

    @Autowired
    private Irole roleRepository;

    @Autowired
    private IPage pageRepository;

    public List<permissionRoles> findAll() {
        return data.findAll();
    }

    public Optional<permissionRoles> findById(int id) {
        return data.findById(id);
    }

    public ResponsesDTO deletePermissionRole(int id) {
        Optional<permissionRoles> permissionRole = findById(id);
        if (!permissionRole.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El permiso de rol no existe");
        }

        data.deleteById(id);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Permiso de rol eliminado correctamente");
    }

    public ResponsesDTO save(Permission_roleDTO permissionRoleDTO) {
        permissionRoles permissionRole = convertToModel(permissionRoleDTO);
        data.save(permissionRole);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Permiso de rol guardado correctamente");
    }

    public ResponsesDTO updatePermissionRole(int id, Permission_roleDTO permissionRoleDTO) {
        Optional<permissionRoles> permissionRole = findById(id);
        if (!permissionRole.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El permiso de rol no existe");
        }

        permissionRoles updatedPermissionRole = permissionRole.get();
        updatedPermissionRole.setPage(permissionRoleDTO.getPage());
        updatedPermissionRole.setRole(permissionRoleDTO.getRole());
        updatedPermissionRole.setType(permissionRoleDTO.getType());

        data.save(updatedPermissionRole);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Permiso de rol actualizado correctamente");
    }

    public Permission_roleDTO convertToDTO(permissionRoles permissionRole) {
        Permission_roleDTO dto = new Permission_roleDTO();
        dto.setPermission_roleid(permissionRole.getPermission_roleid());
        dto.setPage(permissionRole.getPage());
        dto.setRole(permissionRole.getRole());
        dto.setType(permissionRole.getType());
        return dto;
    }

    public permissionRoles convertToModel(Permission_roleDTO permissionRoleDTO) {
        pages page = pageRepository.findById(permissionRoleDTO.getPage().getPageid())
                .orElseThrow(() -> new RuntimeException("Page not found"));
        roles role = roleRepository.findById(permissionRoleDTO.getRole().getRoleid())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return new permissionRoles(
            permissionRoleDTO.getPermission_roleid(),
            page,
            role,
            permissionRoleDTO.getType()
        );
    }
}