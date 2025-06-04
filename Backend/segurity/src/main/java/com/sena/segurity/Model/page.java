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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "page")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class page {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pageId", nullable = false)
        private int pageId;
    @Column(name = "namePage", nullable = false)
        private String namePage;
    @Column(name="path",nullable= false)
        private String path;
    @Column(name="description")
        private String description;
    @OneToMany(mappedBy = "pageId")
        private List<rolePermission> rolePermissions;
    @CreationTimestamp
        private LocalDateTime createPage;
    @UpdateTimestamp
        private LocalDateTime updatePage; 
}
