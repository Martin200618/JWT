package com.sena.segurity.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable= false)
        private int userId;
    @Column(name="userName",nullable= false)
        private String userName;
    @Column(name="lastName")
        private String lastName;
    @Column(name="email",nullable= false)
        private String email;
    @Column(name="password",nullable= false)
        private String password;
    @ManyToOne
    @JoinColumn(name="roleId", nullable= false)
        private roles roleId;
    @Column(name="createUser")
        private LocalDateTime createUser;
    @Column(name="updateUser")
        private LocalDateTime updateUser;
    @Column(name="status", nullable= false)
        private boolean status;
}