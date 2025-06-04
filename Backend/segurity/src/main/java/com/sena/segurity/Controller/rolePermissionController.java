package com.sena.segurity.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.segurity.DTO.rolePermissionDTO;
import com.sena.segurity.Service.rolePermissionService;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}) // Permite solicitudes desde estos orígenes
@RestController
@RequestMapping("api/v1/rolePermissions")
public class rolePermissionController {

    @Autowired
    private rolePermissionService rolePermissionService;

    // Registrar un nuevo permiso de rol
    @PostMapping("/")
    public ResponseEntity<Object> registerRolePermission(@RequestBody rolePermissionDTO rolePermissionDTO) {
        String response = rolePermissionService.save(rolePermissionDTO);
        boolean isSuccessful = response.startsWith("200");

        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),
            isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    // Obtener todos los permisos de rol
    @GetMapping("/")
    public ResponseEntity<Object> getAllRolePermissions() {
        try {
            return new ResponseEntity<>(
                rolePermissionService.findAll(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener los permisos de rol", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Obtener un único permiso de rol por ID
    @GetMapping("/get/{rolePermissionId}")
    public ResponseEntity<Object> getOneRolePermission(@PathVariable("rolePermissionId") int rolePermissionId) {
        Optional<Object> rolePermission = Optional.ofNullable(rolePermissionService.findById(rolePermissionId));
        return rolePermission.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            Map.of("message", "Permiso de rol no encontrado"),
            HttpStatus.NOT_FOUND
        ));
    }

    // Actualizar un permiso de rol por ID
    @PostMapping("/update/{rolePermissionId}")
    public ResponseEntity<Object> updateRolePermission(@PathVariable("rolePermissionId") int rolePermissionId, @RequestBody rolePermissionDTO rolePermissionDTO) {
        try {
            String response = rolePermissionService.update(rolePermissionId, rolePermissionDTO);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar el permiso de rol", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Eliminar un permiso de rol por ID
    @DeleteMapping("/delete/{rolePermissionId}")
    public ResponseEntity<Object> deleteRolePermission(@PathVariable("rolePermissionId") int rolePermissionId) {
        try {
            String response = rolePermissionService.delete(rolePermissionId);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar el permiso de rol", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
