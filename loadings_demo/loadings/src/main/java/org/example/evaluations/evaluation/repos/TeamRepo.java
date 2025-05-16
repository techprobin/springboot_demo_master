// TeamRepo.java
package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepo extends JpaRepository<Team, UUID> {
}