package com.katorzhin.testlab.testlab_application.service;

import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.mapper.EmployeeMapper;
import com.katorzhin.testlab.testlab_application.model.Department;
import com.katorzhin.testlab.testlab_application.model.Employee;
import com.katorzhin.testlab.testlab_application.repository.DepartmentRepository;
import com.katorzhin.testlab.testlab_application.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Long delete(Long id) {
        employeeRepository.deleteById(id);
        return id;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeMapper.convertAllModels(employeeRepository.findAll());
    }


    @Override
    public Employee getBestEmployeeForDepartment(String departmentName) {
        Department dep = departmentRepository.findByName(departmentName);
        if (dep !=null) {
            dep.getEmployees().sort(Comparator.comparingLong(Employee::getSalary).reversed());
            return dep.getEmployees().get(0);
        }
        return null;
    }
}
