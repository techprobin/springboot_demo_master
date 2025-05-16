package org.example.evaluations.services;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.example.evaluations.evaluation.models.Employee;
import org.example.evaluations.evaluation.models.Team;
import org.example.evaluations.evaluation.services.TeamService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TeamServiceIntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeAll
    public void setUp() {
        jdbcTemplate.execute("INSERT INTO team (id, budget, location) VALUES ('e53a45b1-45b1-4f12-a8e2-d7b9c8d8f568', 10000.0, 'New York')");
        jdbcTemplate.execute("INSERT INTO employee (id, name, cost_to_company, team_id) VALUES (1, 'John Doe', 5000.0, 'e53a45b1-45b1-4f12-a8e2-d7b9c8d8f568')");
    }

    @Autowired
    private TeamService teamService;


    @Test
    @Transactional
    public void testGetTeamDetails_DemonstrateLazyLoading_EmployeesNotLoaded() {
        //Arrange
        PersistenceUtil p =  Persistence.getPersistenceUtil();
        UUID teamId = UUID.fromString("e53a45b1-45b1-4f12-a8e2-d7b9c8d8f568");

        //Act
        Team result = teamService.getTeamDetails(teamId);

        //Assert
        assertThat(result).isNotNull();
        assertThat(result.getBudget()).isEqualTo(10000.0);
        assertThat(result.getLocation()).isEqualTo("New York");
        assertFalse(p.isLoaded(result.getEmployees()),"Because of Lazy Loading, Employees will not be loaded if not asked.");
    }

    @Test
    @Transactional
    public void testGetTeamDetails_DemonstrateLazyLoading_EmployeesLoadedWhenAsked() {
        //Arrange
        PersistenceUtil p =  Persistence.getPersistenceUtil();
        UUID teamId = UUID.fromString("e53a45b1-45b1-4f12-a8e2-d7b9c8d8f568");

        //Act
        Team result = teamService.getTeamDetails(teamId);
        assertFalse(p.isLoaded(result.getEmployees()),"Because of Lazy Loading, Employees are not loaded yet as we haven't asked for them.");
        List<Employee> employeeList = result.getEmployees();
        for(Employee employee : employeeList) {
            System.out.println(employee.getName());
        }

        //Assert
        assertThat(result).isNotNull();
        assertThat(result.getBudget()).isEqualTo(10000.0);
        assertThat(result.getLocation()).isEqualTo("New York");
        assertTrue(p.isLoaded(result.getEmployees()),"Because of Lazy Loading, Employees will be loaded as we have asked for them.");
    }
}
