package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.segurity.Model.page;

public interface IPage extends JpaRepository<page, Integer> {
    page findByName(String name);
    page findByUrl(String url);
}