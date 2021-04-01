package com.katorzhin.testlab.testlab_application.dto;

import java.util.List;

public class DepartmentDTO {
    private String name;
    private List<EmployeeDTO> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
