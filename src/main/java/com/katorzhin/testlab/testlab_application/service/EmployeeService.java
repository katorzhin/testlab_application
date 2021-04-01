package com.katorzhin.testlab.testlab_application.service;

import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(Long id);

    void save(Employee employee);

    Long delete(Long id);

    List<EmployeeDTO> getAll();

    Employee getBestEmployeeForDepartment(String department);
}
