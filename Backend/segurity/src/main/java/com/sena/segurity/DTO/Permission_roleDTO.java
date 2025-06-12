package com.sena.segurity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sena.segurity.Model.pages;
import com.sena.segurity.Model.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission_roleDTO {
    @JsonProperty("id")
    private int permission_roleid;
    private pages page;
    private roles role;
    private String type;
}