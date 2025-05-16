// TeamService.java
package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Team;
import org.example.evaluations.evaluation.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    public Team getTeamDetails(UUID teamId) {
        return teamRepo.findById(teamId).orElse(null);
    }
}