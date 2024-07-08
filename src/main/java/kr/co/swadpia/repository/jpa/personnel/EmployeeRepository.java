package kr.co.swadpia.repository.jpa.personnel;

import kr.co.swadpia.entity.personnel.Employee;
import kr.co.swadpia.repository.jpa.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends GenericRepository<Employee> {
}
