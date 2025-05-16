// IdentityProof.java
package org.example.evaluations.evaluation.models;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class IdentityProof {
    @Id
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}