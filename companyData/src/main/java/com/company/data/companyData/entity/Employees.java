package com.company.data.companyData.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employees {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "full_name")
//    @Pattern(regexp = "^[A-Z]{1}[a-z]{2}[a-z]*", message = "Please Enter Valid Name")
    private String fullName;

    //    @Pattern(regexp = "^[A-Z]{1}[a-z]{2}[a-z]*", message = "Please Enter Valid Name")
    @Column(name = "address")
    private String address;

    @Column(name = "Contact", unique = true)
    private String contact;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL)
    private List<EmployeesTransaction> employeesTransactions = new ArrayList<>();

    public List<EmployeesTransaction> getEmployeesTransactions() {
        return employeesTransactions;
    }

    public void setEmployeesTransactions(List<EmployeesTransaction> employeesTransactions) {
        this.employeesTransactions = employeesTransactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employees [address=" + address + ", contact=" + contact + ", department=" + department
                + ", fullName=" + fullName + ", id=" + id + "]";
    }

    public Employees(int id, String fullName, String address, String contact, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.contact = contact;
        this.department = department;
    }

    public Employees() {

    }

    public void updateEmployeesData(Employees employees) {
        address = employees.getAddress() != null ? employees.getAddress() : address;
        contact = employees.getContact() != null ? employees.getContact() : contact;
        fullName = employees.getFullName() != null ? employees.getFullName() : fullName;
    }

}
