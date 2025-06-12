package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.segurity.Model.roles;

public interface Irole extends JpaRepository<roles, Integer> {
}