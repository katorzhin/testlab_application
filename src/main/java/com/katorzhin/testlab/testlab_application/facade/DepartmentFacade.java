package com.katorzhin.testlab.testlab_application.facade;

import com.katorzhin.testlab.testlab_application.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentFacade {

    List<DepartmentDTO> getAllDepartments();

    List<DepartmentDTO> getAllDepartmentsWithSortedEmployees();


}
