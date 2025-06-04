package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.segurity.Model.roles;

public interface IRoles extends JpaRepository<roles, Integer>{
    roles findByName(String name);
    roles findByDescription(String description);
}