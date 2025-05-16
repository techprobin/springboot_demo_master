// Employee.java
package org.example.evaluations.evaluation.models;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    private Double costToCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<IdentityProof> proofs;
}