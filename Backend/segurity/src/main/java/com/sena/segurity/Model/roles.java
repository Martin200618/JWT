package com.sena.segurity.Model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", nullable = false)
        private int roleId;
    @Column(name = "nameRole", nullable = false)
        private String nameRole;
    @Column(name = "description")
        private String description;
    @OneToMany(mappedBy = "roleId")
        private List<user> users;
    @OneToMany(mappedBy = "role")
        private List<rolePermission> rolePermissions;
    @CreationTimestamp
        private LocalDateTime createRol;
    @UpdateTimestamp
        private LocalDateTime updateRol;
}