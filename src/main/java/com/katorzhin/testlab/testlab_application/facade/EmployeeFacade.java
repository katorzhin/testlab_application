package com.katorzhin.testlab.testlab_application.facade;

import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmployeeFacade {

    boolean uploadEmployee(MultipartFile file) throws IOException;

    EmployeeDTO getBestEmployeeForDepartment(String departmentName);


}
