package com.katorzhin.testlab.testlab_application.repository;

import com.katorzhin.testlab.testlab_application.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
