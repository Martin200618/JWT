package com.sena.segurity.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "page")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class pages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pageid")
    private int pageid;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "url", length = 255, nullable = false)
    private String url;
}