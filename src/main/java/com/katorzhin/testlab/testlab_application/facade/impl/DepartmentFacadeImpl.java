package com.katorzhin.testlab.testlab_application.facade.impl;

import com.katorzhin.testlab.testlab_application.dto.DepartmentDTO;
import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.facade.DepartmentFacade;
import com.katorzhin.testlab.testlab_application.mapper.DepartmentMapper;
import com.katorzhin.testlab.testlab_application.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class DepartmentFacadeImpl implements DepartmentFacade {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentMapper.convertAllModels(departmentService.getAll());
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentsWithSortedEmployees() {
        List<DepartmentDTO> departments = departmentMapper.convertAllModels(departmentService.getAll());
        departments.forEach(dep -> dep.getEmployees().sort(Comparator.comparingLong(EmployeeDTO::getSalary).reversed()));
        return departments;
    }
}
