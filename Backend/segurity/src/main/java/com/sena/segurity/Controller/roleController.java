package com.sena.segurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.segurity.DTO.ResponsesDTO;
import com.sena.segurity.DTO.roleDTO;
import com.sena.segurity.Model.roles;
import com.sena.segurity.Service.roleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class roleController {
    @Autowired
    private roleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<roles>> getAllRoles() {
        List<roles> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable int id) {
        Optional<roles> rol = roleService.findById(id);
        if (!rol.isPresent()) {
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rol.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createRole(@RequestBody roleDTO roleDTO) {
        ResponsesDTO response = roleService.save(roleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable int id, @RequestBody roleDTO roleDTO) {
        ResponsesDTO response = roleService.updateRole(id, roleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable int id) {
        ResponsesDTO response = roleService.deleteRole(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}