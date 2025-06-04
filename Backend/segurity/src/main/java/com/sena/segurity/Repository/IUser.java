package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.segurity.Model.user;

public interface IUser extends JpaRepository<user, Integer>{

}