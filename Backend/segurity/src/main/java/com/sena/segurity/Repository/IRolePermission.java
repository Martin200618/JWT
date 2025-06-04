package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.segurity.Model.rolePermission;

public interface IRolePermission extends JpaRepository<rolePermission, Integer>{
    rolePermission findByRoleId(int roleId);
}