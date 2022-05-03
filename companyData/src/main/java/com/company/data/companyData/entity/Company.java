package com.company.data.companyData.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", unique = true)
    @NotBlank()
//    @Pattern(regexp = "^[A-Z]{1}[a-z]{2}[a-z]*", message = "Please Enter Valid Name")
    private String name;

    @Column(name = "address")
    @NotBlank(message = "Address Should not be Blank")
    private String address;

    @Column(name = "contact", unique = true)
    @Size(min = 3, max = 11)
    @Digits(integer = 11, fraction = 0, message = "Please Enter Valid Number")
    private String contact;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Department> department = new ArrayList<>();

    @JsonIgnore
    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    @JsonIgnore
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

    @Override
    public String toString() {
        return "company [address=" + address + ", contact=" + contact + ", id=" + id + ", name=" + name + "]";
    }

    public Company(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Company() {

    }

    public void updateCompanyData(Company company) {
        name = company.getName() != null ? company.getName() : name;
        address = company.getAddress() != null ? company.getAddress() : address;
        contact = company.getContact() != null ? company.getContact() : contact;
    }

}
