package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.segurity.Model.request;

public interface IRequest extends JpaRepository<request, Integer> {
    request findByToken(String token);
    request findByUserId(int userId);
    request findByRequestId(int requestId);
}
