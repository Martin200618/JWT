package com.sena.segurity.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.segurity.DTO.userDTO;
import com.sena.segurity.Model.user;
import com.sena.segurity.Repository.IUser;

@Service
public class userService {

    @Autowired
    private IUser userRepository;

    // Obtener todos los usuarios
    public List<user> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los usuarios: " + e.getMessage());
        }
    }

    // Obtener usuario por ID
    public Optional<user> findById(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario con ID: " + userId + ", Error: " + e.getMessage());
        }
    }

    // Registrar un usuario
    public String save(userDTO userDTO) {
        if (userDTO == null || !isValiduser(userDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del usuario son inválidos";
        }

        try {
            user user = convertToModel(userDTO);
            userRepository.save(user);
            return HttpStatus.OK.toString() + ": Usuario registrado exitosamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al registrar el usuario, Detalle: " + e.getMessage();
        }
    }

    // Actualizar un usuario
    public String update(int userId, userDTO userDTO) {
        Optional<user> existingUser = findById(userId);

        if (!existingUser.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El usuario con ID " + userId + " no existe o ya fue eliminado";
        }

        try {
            user userToUpdate = existingUser.get();
            userToUpdate.setUserName(userDTO.getUserName());
            userToUpdate.setLastName(userDTO.getLastName());
            userToUpdate.setEmail(userDTO.getEmail());
            userToUpdate.setPassword(userDTO.getPassword());
            userToUpdate.getRoleId();// Se asume que el DTO tiene el rol correctamente definido
            userToUpdate.setStatus(userDTO.isStatus());
            userRepository.save(userToUpdate);
            return HttpStatus.OK.toString() + ": Usuario actualizado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar el usuario, Detalle: " + e.getMessage();
        }
    }

    // Eliminar usuario por ID
    public String delete(int userId) {
        Optional<user> user = findById(userId);

        if (!user.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El usuario con ID " + userId + " no existe o ya fue eliminado";
        }

        try {
            userRepository.deleteById(userId);
            return HttpStatus.OK.toString() + ": Usuario eliminado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar el usuario, Detalle: " + e.getMessage();
        }
    }

    // Convertir DTO a modelo
    private user convertToModel(userDTO userDTO) {
        user user = new user();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.getRoleId(); // Asumiendo que el DTO tiene el rol correctamente definido
        user.setStatus(userDTO.isStatus());
        return user;
    }

    // Validación de datos de usuario
    private boolean isValiduser(userDTO userDTO) {
        return userDTO.getUserName() != null && !userDTO.getUserName().trim().isEmpty()
                && userDTO.getEmail() != null && !userDTO.getEmail().trim().isEmpty()
                && userDTO.getPassword() != null && !userDTO.getPassword().trim().isEmpty();
    }
}
