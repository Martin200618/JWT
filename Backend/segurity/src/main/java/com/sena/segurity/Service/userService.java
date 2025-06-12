package com.sena.segurity.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sena.segurity.Service.jwt.jwtServices;
import com.sena.segurity.DTO.ResponseLogin;
import com.sena.segurity.DTO.ResponsesDTO;
import com.sena.segurity.DTO.requestLoginDTO;
import com.sena.segurity.DTO.requestRegisterUserDTO;
import com.sena.segurity.DTO.userDTO;
import com.sena.segurity.Model.roles;
import com.sena.segurity.Model.user;
import com.sena.segurity.Repository.IUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userService  {

    private final IUser data;
    private final jwtServices jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public List<user> findAll() {
        return data.findAll();
    }

    public Optional<user> findById(int id) {
        return data.findById(id);
    }

    public Optional<user> findByUsername(String username) {
        return data.findByUsername(username);
    }

    public Optional<user> findByEmail(String Email) {
        return data.findByEmail(Email);
    }

    public ResponsesDTO deleteUser(int id) {
        Optional<user> usuario = findById(id);
        if (!usuario.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El usuario no existe");
        }

        data.deleteById(id);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Usuario eliminado correctamente");
    }

    

    public ResponsesDTO save(requestRegisterUserDTO userDTO) {
        user usuario = convertToModelRegister(userDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        data.save(usuario);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Usuario guardado correctamente");
    }

    public ResponseLogin login(requestLoginDTO login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()));
        UserDetails user=data.findByUsername(login.getUsername()).orElseThrow();
        ResponseLogin response=new ResponseLogin(jwtService.getToken(user));
        return response;

    }

    public ResponsesDTO updateUser(int id, userDTO userDTO) {
        Optional<user> usuario = findById(id);
        if (!usuario.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El usuario no existe");
        }

        user updatedUser = usuario.get();
        updatedUser.setUsername(userDTO.getUsername());
        updatedUser.setPassword(userDTO.getPassword());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setEnabled(userDTO.isEnabled());
        updatedUser.setRoles(userDTO.getRole());

        data.save(updatedUser);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Usuario actualizado correctamente");
    }

    public user convertToModelRegister(requestRegisterUserDTO usuario) {
        roles rol = new roles();
        // asignamos rol por defecto
        // registrar el rol 1 como usuario estandar
        rol.setRoleid(1);
        return new user(
            0, 
            usuario.getUsername(), 
            usuario.getPassword(), 
            usuario.getEmail(), 
            rol, 
            true
        );
    }

    public user convertToModel(userDTO userDTO) {
        roles rol =new roles();
        //rol por defecto, recordar registrar en base datos este como rol default
        rol.setRoleid(1);
        return new user(
            0, 
            userDTO.getUsername(),
            userDTO.getPassword(), 
            userDTO.getEmail(), 
            rol, 
            userDTO.isEnabled()
        );
    }
}