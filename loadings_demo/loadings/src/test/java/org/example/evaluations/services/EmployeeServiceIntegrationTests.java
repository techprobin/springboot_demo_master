package org.example.evaluations.services;

import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUtil;
import jakarta.transaction.Transactional;
import org.example.evaluations.evaluation.models.Employee;
import org.example.evaluations.evaluation.models.IdentityProof;
import org.example.evaluations.evaluation.services.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class EmployeeServiceIntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeAll
    public void setUp() {
        jdbcTemplate.execute("INSERT INTO team (id, budget, location) VALUES ('e53a45b1-45b1-4f12-a8e2-d7b9c8d8f569', 30000.0, 'New Jersey')");
        jdbcTemplate.execute("INSERT INTO employee (id, name, cost_to_company, team_id) VALUES (2, 'Rachit', 5000.0, 'e53a45b1-45b1-4f12-a8e2-d7b9c8d8f569')");
        jdbcTemplate.execute("INSERT INTO identity_proof(id,name,employee_id) VALUES(1,'GREEN_CARD',2)");
        jdbcTemplate.execute("INSERT INTO identity_proof(id,name,employee_id) VALUES(2,'DRIVING_LICENSE',2)");
    }

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Transactional
    public void testGetEmployeeDetails_DemonstrateEagerLoading_WhenNotAskedForProofs() {
        //Arrange
        PersistenceUtil p =  Persistence.getPersistenceUtil();

        //Act
        Employee result = employeeService.getEmployeeDetails(2L);

        //Assert
        assertThat(result).isNotNull();
        assertTrue(p.isLoaded(result.getProofs()),"Because of Eager Loading, identity proofs will also be loaded even when not asked.");
    }

    @Test
    @Transactional
    public void testGetEmployeeDetails_DemonstrateEagerLoading_WhenAskedForProofs() {
        //Arrange
        PersistenceUtil p =  Persistence.getPersistenceUtil();

        //Act
        Employee result = employeeService.getEmployeeDetails(2L);
        assertTrue(p.isLoaded(result.getProofs()),"Because of Eager Loading, identity proofs will also be loaded even when not asked.");
        List<IdentityProof> proofList = result.getProofs();
        for(IdentityProof proof : proofList) {
            System.out.println(proof.getName());
        }

        //Assert
        assertThat(result).isNotNull();
        assertTrue(p.isLoaded(result.getProofs()),"Because of Eager Loading, identity proofs will also be loaded no matter asked or not.");
    }
}
