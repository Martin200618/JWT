package com.sena.segurity.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class rolePermission {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "rolePermissionId", nullable = false)
        private int rolePermissionId;
    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
        private roles roleId;
    @ManyToOne
    @JoinColumn(name = "pageId", nullable = false)
        private page pageId;
    @Enumerated(EnumType.STRING)
        private permission permission;
    @CreationTimestamp
        private LocalDateTime createRolePermission;
    @UpdateTimestamp
        private LocalDateTime updateRolePermission;

    public enum permission {
        READ,
        CREATED,
        DELETE,
        UPDATE
    }
}
