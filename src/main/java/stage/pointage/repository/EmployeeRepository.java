package stage.pointage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.pointage.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}