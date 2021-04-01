package com.katorzhin.testlab.testlab_application.service;

import com.katorzhin.testlab.testlab_application.model.Department;
import com.katorzhin.testlab.testlab_application.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;
    @Override
    public Department getOrSave(Department department) {
        Department existing = repository.findByName(department.getName());
        if (existing == null) {
           return repository.save(department);
        }
        return existing;
    }

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }
}
