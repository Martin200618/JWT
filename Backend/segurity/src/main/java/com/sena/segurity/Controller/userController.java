package com.sena.segurity.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}) // Permite solicitudes de estos orígenes
@RestController
@RequestMapping("api/v1/users")
public class userController {
    // Registrar un nuevo usuario
    /*/@PostMapping("/")
    public ResponseEntity<Object> registerUser(@RequestBody userDTO userDTO) {
        String response = userService.save(userDTO);
        boolean isSuccessful = response.startsWith("200");

        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),
            isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    // Obtener todos los usuarios
    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        try {
            return new ResponseEntity<>(
                userService.findAll(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener los usuarios", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Obtener un único usuario por ID
    @GetMapping("/get/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable("userId") int userId) {
        Optional<Object> user = Optional.ofNullable(userService.findById(userId));
        return user.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            Map.of("message", "Usuario no encontrado"),
            HttpStatus.NOT_FOUND
        ));
    }

    // Actualizar un usuario por ID
    @PostMapping("/update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") int userId, @RequestBody userDTO userDTO) {
        try {
            String response = userService.update(userId, userDTO);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar el usuario", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") int userId) {
        try {
            String response = userService.delete(userId);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar el usuario", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }/*/

    @GetMapping("/profile/")
    public ResponseEntity<String> getProfile(){
        return new ResponseEntity<>("end-point privado profile", HttpStatus.OK);
    }
}