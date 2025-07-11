package com.sena.segurity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sena.segurity.Model.roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDTO{
    @JsonProperty("id")
    private int userid; 
    private String username;
    private String password;
    private String email;
    private roles role;
    private boolean enabled;
}