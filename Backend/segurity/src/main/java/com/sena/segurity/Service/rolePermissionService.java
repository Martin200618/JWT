package com.sena.segurity.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.segurity.DTO.rolePermissionDTO;
import com.sena.segurity.Model.rolePermission;
import com.sena.segurity.Repository.IRolePermission;

@Service
public class rolePermissionService {

    @Autowired
    private IRolePermission rolePermissionRepository;

    // Obtener todos los permisos de rol
    public List<rolePermission> findAll() {
        try {
            return rolePermissionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los permisos de rol: " + e.getMessage());
        }
    }

    // Obtener un permiso de rol por ID
    public Optional<rolePermission> findById(int rolePermissionId) {
        try {
            return rolePermissionRepository.findById(rolePermissionId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el permiso de rol con ID: " + rolePermissionId + ", Error: " + e.getMessage());
        }
    }

    // Registrar un permiso de rol
    public String save(rolePermissionDTO rolePermissionDTO) {
        if (rolePermissionDTO == null || !isValidRolePermission(rolePermissionDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del permiso de rol son inválidos";
        }

        try {
            rolePermission rolePermission = converToModel(rolePermissionDTO);
            rolePermissionRepository.save(rolePermission);
            return HttpStatus.OK.toString() + ": Permiso de rol registrado exitosamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al registrar el permiso de rol, Detalle: " + e.getMessage();
        }
    }

    // Actualizar un permiso de rol
    public String update(int rolePermissionId, rolePermissionDTO rolePermissionDTO) {
        Optional<rolePermission> existingRolePermission = findById(rolePermissionId);

        if (!existingRolePermission.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El permiso de rol con ID " + rolePermissionId + " no existe o ya fue eliminado";
        }

        try {
            rolePermission rolePermissionToUpdate = existingRolePermission.get();
            rolePermissionToUpdate.getRoleId();
            rolePermissionToUpdate.getPageId();
            rolePermissionRepository.save(rolePermissionToUpdate);
            return HttpStatus.OK.toString() + ": Permiso de rol actualizado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar el permiso de rol, Detalle: " + e.getMessage();
        }
    }

    // Eliminar un permiso de rol por ID
    public String delete(int rolePermissionId) {
        Optional<rolePermission> rolePermission = findById(rolePermissionId);

        if (!rolePermission.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El permiso de rol con ID " + rolePermissionId + " no existe o ya fue eliminado";
        }

        try {
            rolePermissionRepository.deleteById(rolePermissionId);
            return HttpStatus.OK.toString() + ": Permiso de rol eliminado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar el permiso de rol, Detalle: " + e.getMessage();
        }
    }

    // Conversión de DTO a Entidad
    private rolePermission converToModel(rolePermissionDTO rolePermissionDTO) {
        rolePermission rolePermission = new rolePermission();
        rolePermission.getRoleId();
        rolePermission.getPageId();
        rolePermission.setCreateRolePermission(rolePermissionDTO.getCreateRolePermission());
        rolePermission.setUpdateRolePermission(rolePermissionDTO.getUpdateRolePermission());
        return rolePermission;
    }

    // Validación de datos de permiso de rol
    private boolean isValidRolePermission(rolePermissionDTO rolePermissionDTO) {
        return rolePermissionDTO.getRole() != null
                && rolePermissionDTO.getPage() != null;
    }
}
