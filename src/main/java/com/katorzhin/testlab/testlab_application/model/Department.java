package com.katorzhin.testlab.testlab_application.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Department extends BaseEntity   {


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> employees;

    @Column(unique=true)
    private String name;


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
