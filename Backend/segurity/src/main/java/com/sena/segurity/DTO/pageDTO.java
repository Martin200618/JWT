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
public class pageDTO {
    private int pageId;
    private String namePage;
    private String path;
    private String description;
    private LocalDateTime createPage;
    private LocalDateTime updatePage;
}