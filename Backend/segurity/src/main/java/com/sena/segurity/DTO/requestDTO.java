package com.sena.segurity.DTO;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class requestDTO {
    private int requestId;
    private String token;
    private userDTO user; // Usamos UserDTO para evitar exponer la entidad completa
    private LocalDateTime createRequest;
    private LocalDateTime updateRequest;
}