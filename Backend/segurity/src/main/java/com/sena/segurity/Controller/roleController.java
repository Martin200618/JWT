package com.sena.segurity.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.segurity.DTO.roleDTO;
import com.sena.segurity.Service.roleService;


@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}) // Permite solicitudes desde estos orígenes
@RestController
@RequestMapping("api/v1/roles")
public class roleController {

    @Autowired
    private roleService roleService;

    // Registrar un nuevo rol
    @PostMapping("/")
    public ResponseEntity<Object> registerRole(@RequestBody roleDTO roleDTO) {
        String response = roleService.save(roleDTO);
        boolean isSuccessful = response.startsWith("200");

        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),
            isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    // Obtener todos los roles
    @GetMapping("/")
    public ResponseEntity<Object> getAllRoles() {
        try {
            return new ResponseEntity<>(
                roleService.findAll(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener los roles", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Obtener un único rol por ID
    @GetMapping("/get/{roleId}")
    public ResponseEntity<Object> getOneRole(@PathVariable("roleId") int roleId) {
        Optional<Object> role = Optional.ofNullable(roleService.findById(roleId));
        return role.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            Map.of("message", "Rol no encontrado"),
            HttpStatus.NOT_FOUND
        ));
    }

    // Actualizar un rol por ID
    @PostMapping("/update/{roleId}")
    public ResponseEntity<Object> updateRole(@PathVariable("roleId") int roleId, @RequestBody roleDTO roleDTO) {
        try {
            String response = roleService.update(roleId, roleDTO);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar el rol", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Eliminar un rol por ID
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<Object> deleteRole(@PathVariable("roleId") int roleId) {
        try {
            String response = roleService.delete(roleId);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar el rol", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
