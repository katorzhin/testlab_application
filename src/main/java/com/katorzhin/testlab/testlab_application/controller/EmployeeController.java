package com.katorzhin.testlab.testlab_application.controller;

import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.facade.EmployeeFacade;
import com.katorzhin.testlab.testlab_application.message.ResponseMessage;
import com.katorzhin.testlab.testlab_application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeFacade employeeFacade;

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> employee = this.employeeService.getAll();
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (employeeFacade.uploadEmployee(file)){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("SUCCESS"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("ERROR"));
    }

    @GetMapping("/best")
    public ResponseEntity<EmployeeDTO>getBestEmployeeForDepartment(@RequestParam(required = true) String department) {
        EmployeeDTO result = employeeFacade.getBestEmployeeForDepartment(department);
        if (result.getName() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
