package com.katorzhin.testlab.testlab_application.controller;

import com.katorzhin.testlab.testlab_application.dto.DepartmentDTO;
import com.katorzhin.testlab.testlab_application.facade.DepartmentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentFacade departmentFacade;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll(
            @RequestParam(required = false, defaultValue = "false") boolean sorted) {
        List<DepartmentDTO> departments;

        if (sorted) {
            departments = departmentFacade.getAllDepartmentsWithSortedEmployees();
        } else {
            departments = departmentFacade.getAllDepartments();
        }

        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

}
