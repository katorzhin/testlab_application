package com.katorzhin.testlab.testlab_application.service;

import com.katorzhin.testlab.testlab_application.model.Department;

import java.util.List;

public interface DepartmentService {

    Department getOrSave(Department department);

    List<Department> getAll();
}
