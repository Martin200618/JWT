package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.segurity.Model.permissionRoles;

public interface Ipermission_role extends JpaRepository<permissionRoles, Integer> {
}