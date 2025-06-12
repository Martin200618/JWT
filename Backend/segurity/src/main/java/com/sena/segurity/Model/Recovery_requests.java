package com.sena.segurity.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "recovery_request")
public class Recovery_requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recovery_requestid")
    private int recovery_requestid;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "token", length = 255, nullable = false)
    private String token;

    @Column(name = "expiration_time", nullable = false)
    private long expirationTime;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private user user;
}