package com.katorzhin.testlab.testlab_application.mapper;

import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.model.Department;
import com.katorzhin.testlab.testlab_application.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {

    public Employee dtoToModel(EmployeeDTO dto) {
        Employee result = new Employee();
        if (dto == null) {
            return new Employee();
        }
        result.setDepartment(getDepartament(dto));
        result.setName(dto.getName());
        result.setSalary(dto.getSalary());
        return result;
    }

    public EmployeeDTO modelToDto(Employee model) {
        EmployeeDTO result = new EmployeeDTO();
        if (model == null) {
            return new EmployeeDTO();
        }
        result.setSalary(model.getSalary());
        result.setName(model.getName());
        if (model.getDepartment() != null) {
            result.setDepartment(model.getDepartment().getName());
        }
        return result;
    }

    public List<EmployeeDTO> convertAllModels(List<Employee> modelList) {
        List<EmployeeDTO> result = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            modelList.forEach(model -> {
                result.add(modelToDto(model));
            });
        }
        return result;
    }

    private Department getDepartament(EmployeeDTO dto) {
        Department res = new Department();
        if (dto.getDepartment() == null) {
            return res;
        }
        res.setName(dto.getDepartment());
        return res;
    }

}
