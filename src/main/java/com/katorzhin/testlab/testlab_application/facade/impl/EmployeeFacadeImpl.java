package com.katorzhin.testlab.testlab_application.facade.impl;

import com.katorzhin.testlab.testlab_application.dto.EmployeeDTO;
import com.katorzhin.testlab.testlab_application.facade.EmployeeFacade;
import com.katorzhin.testlab.testlab_application.mapper.EmployeeMapper;
import com.katorzhin.testlab.testlab_application.model.Employee;
import com.katorzhin.testlab.testlab_application.service.DepartmentService;
import com.katorzhin.testlab.testlab_application.service.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

@Component
public class EmployeeFacadeImpl implements EmployeeFacade {

    private static final char DELIMITER = ';';
    private static String TYPE_CSV = "text/csv";
    private final String[] headers = {"Employee", "Department", "Salary"};

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public EmployeeDTO getBestEmployeeForDepartment(String departmentName) {
        return employeeMapper.modelToDto(employeeService.getBestEmployeeForDepartment(departmentName));
    }

    @Override
    public boolean uploadEmployee(MultipartFile file) throws IOException {
        File csv = getCsv(file);
        try {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withDelimiter(DELIMITER)
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(new FileReader(csv));
            records.forEach(record -> {
                EmployeeDTO dto = new EmployeeDTO();
                dto.setName(record.get(0));
                dto.setSalary(Long.valueOf(record.get(2)));
                dto.setDepartment(record.get(1));
                Employee model = employeeMapper.dtoToModel(dto);
                model.setDepartment(departmentService.getOrSave(model.getDepartment()));
                employeeService.save(model);
            });
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            csv.delete();
        }
    }

    private File getCsv(MultipartFile file) throws IOException {
        if (file != null) {
            if (isCsv(file)) {
                File convFile = new File(file.getOriginalFilename());
                convFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(file.getBytes());
                fos.close();
                return convFile;
            }
            return null;
        }
        return null;
    }

    private boolean isCsv(MultipartFile file) {
        if (file != null && TYPE_CSV.equals(file.getContentType())) {
            return true;
        }
        return false;
    }
}
