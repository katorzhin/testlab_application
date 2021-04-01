package com.katorzhin.testlab.testlab_application.repository;

import com.katorzhin.testlab.testlab_application.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);
}
