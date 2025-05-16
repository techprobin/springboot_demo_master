package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee, Long> {
}
