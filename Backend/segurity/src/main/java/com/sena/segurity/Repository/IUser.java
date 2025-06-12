package com.sena.segurity.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.segurity.Model.user;

public interface IUser extends JpaRepository<user, Integer> {
    Optional<user> findByUsername(String username);
    Optional<user> findByEmail(String email);
    List<user> findAllByEnabled(boolean enabled);
}