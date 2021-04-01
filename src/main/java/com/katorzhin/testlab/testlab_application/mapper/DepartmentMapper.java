package com.katorzhin.testlab.testlab_application.mapper;

import com.katorzhin.testlab.testlab_application.dto.DepartmentDTO;
import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.model.Department;
import com.katorzhin.testlab.testlab_application.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    public DepartmentDTO modelToDto(Department department) {
        DepartmentDTO res = new DepartmentDTO();
        if (department == null) {
            return res;
        }
        res.setName(department.getName());
        res.setEmployees(employeeMapper.convertAllModels(department.getEmployees()));
        return res;
    }

    public List<DepartmentDTO> convertAllModels(List<Department> modelList) {
        List<DepartmentDTO> result = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            modelList.forEach(model -> result.add(modelToDto(model)));
        }
        return result;
    }
}
