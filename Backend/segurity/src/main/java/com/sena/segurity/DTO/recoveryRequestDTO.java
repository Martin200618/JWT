package com.sena.segurity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sena.segurity.Model.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class recoveryRequestDTO {
    @JsonProperty("id")
    private int recovery_requestid;
    private String email;
    private String token;
    private long expirationTime;
    private user user;
}