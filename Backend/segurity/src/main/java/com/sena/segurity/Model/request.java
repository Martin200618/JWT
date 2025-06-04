package com.sena.segurity.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class request {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "requestId", nullable = false)
        private int requestId;
    @Column(name = "token", nullable = false)
        private String token;
    @ManyToOne
        private user userId;
    @CreationTimestamp
        private LocalDateTime createRequest;
    @UpdateTimestamp
     private LocalDateTime updateRequest;
}
