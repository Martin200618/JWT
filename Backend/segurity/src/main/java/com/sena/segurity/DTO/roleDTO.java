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
public class roleDTO {
    private int roleId;
    private String nameRole;
    private String description;
    private LocalDateTime createRol;
    private LocalDateTime updateRol;
}