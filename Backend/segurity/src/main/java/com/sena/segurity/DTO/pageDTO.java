package com.sena.segurity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class pageDTO {
    @JsonProperty("id")
    private int pageid;
    private String name;
    private String url;
    private String description;
}