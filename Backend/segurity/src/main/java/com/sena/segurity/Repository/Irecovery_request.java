package com.sena.segurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.segurity.Model.Recovery_requests;

public interface Irecovery_request extends JpaRepository<Recovery_requests, Integer> {
}