package com.sena.segurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {
    private int userId;
    private String userName;
    private String lastName;
    private String email;
    private String password;
    private roleDTO role; // Usamos un DTO para evitar exponer la entidad completa
    private boolean status;
}