package com.sena.segurity.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "permission_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class permissionRoles{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_roleid")
    private int permission_roleid;

    @ManyToOne
    @JoinColumn(name = "pageid", nullable = false)
    private pages page;

    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private roles role;

    @Column(name = "type", nullable = false)
    private String type;
}
