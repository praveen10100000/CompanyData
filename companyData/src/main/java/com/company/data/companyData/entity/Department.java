package com.company.data.companyData.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "department_name", unique = true)
//    @Pattern(regexp = "^[A-Z]{1}[a-z]{2}[a-z]*", message = "Please Enter Valid Name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "department")
    private List<Employees> employees = new ArrayList<>();

    @JsonIgnore
    public List<Employees> getEmployee() {
        return employees;
    }

    public void setEmployee(List<Employees> employee) {
        this.employees = employee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + ", company=" + company + ", employees=" + employees + '}';
    }

    public Department(int id, String name, int comapny_id) {
        this.id = id;
        this.name = name;
    }

    public Department() {

    }

    public void updateDepartmentData(Department department) {
        name = department.getName() != null ? department.getName() : name;
    }


}
