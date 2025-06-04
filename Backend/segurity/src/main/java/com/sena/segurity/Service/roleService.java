package com.sena.segurity.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.segurity.DTO.roleDTO;
import com.sena.segurity.Model.roles;
import com.sena.segurity.Repository.IRoles;

@Service
public class roleService {

    @Autowired
    private IRoles roleRepository;

    // Obtener todos los roles
    public List<roles> findAll() {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los roles: " + e.getMessage());
        }
    }

    // Obtener un rol por ID
    public Optional<roles> findById(int roleId) {
        try {
            return roleRepository.findById(roleId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el rol con ID: " + roleId + ", Error: " + e.getMessage());
        }
    }

    // Registrar un rol
    public String save(roleDTO roleDTO) {
        if (roleDTO == null || !isValidRole(roleDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del rol son inválidos";
        }

        try {
            roles role = converToModel(roleDTO);
            roleRepository.save(role);
            return HttpStatus.OK.toString() + ": Rol registrado exitosamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al registrar el rol, Detalle: " + e.getMessage();
        }
    }

    // Actualizar un rol
    public String update(int roleId, roleDTO roleDTO) {
        Optional<roles> existingRole = findById(roleId);

        if (!existingRole.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El rol con ID " + roleId + " no existe o ya fue eliminado";
        }

        try {
            roles roleToUpdate = existingRole.get();
            roleToUpdate.setNameRole(roleDTO.getNameRole());
            roleToUpdate.setDescription(roleDTO.getDescription());
            roleRepository.save(roleToUpdate);
            return HttpStatus.OK.toString() + ": Rol actualizado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar el rol, Detalle: " + e.getMessage();
        }
    }

    // Eliminar un rol por ID
    public String delete(int roleId) {
        Optional<roles> role = findById(roleId);

        if (!role.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El rol con ID " + roleId + " no existe o ya fue eliminado";
        }

        try {
            roleRepository.deleteById(roleId);
            return HttpStatus.OK.toString() + ": Rol eliminado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar el rol, Detalle: " + e.getMessage();
        }
    }

    // Conversión de DTO a modelo
    private roles converToModel(roleDTO roleDTO) {
        roles role = new roles();
        role.setNameRole(roleDTO.getNameRole());
        role.setDescription(roleDTO.getDescription());
        return role;
    }

    // Validación de datos de rol
    private boolean isValidRole(roleDTO roleDTO) {
        return roleDTO.getNameRole() != null && !roleDTO.getNameRole().trim().isEmpty();
    }
}
