package com.codingshuttle.youtube.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique = false, length = 50)
    private String policyNumber;

    @Column(nullable=false, length = 100)
    private String provider;

    @Column(nullable=false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable=false, updatable = false)
    private LocalDate createdAt;

    @OneToOne(mappedBy = "insurance") //inverse side
    private Patient patient;
}
