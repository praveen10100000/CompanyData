package com.company.data.companyData.controller;


import com.company.data.companyData.customResponse.EmployeesCustomResponse;
import com.company.data.companyData.entity.Department;
import com.company.data.companyData.entity.Employees;
import com.company.data.companyData.entity.ResponseInJson;
import com.company.data.companyData.repo.DepartmentRepo;
import com.company.data.companyData.repo.EmployeesRepo;
import com.company.data.companyData.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeesRepo employeesRepo;


    @PostMapping("/")
    public ResponseEntity<?> addEmployees(@RequestBody EmployeesCustomResponse employeesCustomRequest,
                                          Employees employees) {
        Department department1 = departmentRepo.getById(employeesCustomRequest.getDepartmentId());
        employees.setAddress(employeesCustomRequest.getAddress());
        employees.setContact(employeesCustomRequest.getContact());
        employees.setFullName(employeesCustomRequest.getFullName());
        employees.setDepartment(department1);
        employees.setAddress(companyService.Capitalize(employeesCustomRequest.getAddress()));
        employees.setFullName(companyService.Capitalize(employeesCustomRequest.getFullName()));
        employeesRepo.save(employees);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Added Successfully");
        responseInJson.setData(department1);
        return ResponseEntity.ok(responseInJson);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employees>> getEmployeesData() {
        List<Employees> list = new ArrayList<>();
        employeesRepo.findAll().forEach(emp -> list.add(emp));
        return ResponseEntity.ok(list);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateEmployeesData(@RequestBody Employees employees) {
        Employees oldEmployees = employeesRepo.getById(employees.getId());
        oldEmployees.updateEmployeesData(employees);
        employees.setFullName(companyService.Capitalize(employees.getFullName()));
        employees.setAddress(companyService.Capitalize(employees.getAddress()));
        employeesRepo.save(oldEmployees);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Updated Successfully");
        responseInJson.setData(oldEmployees);
        return ResponseEntity.ok(responseInJson);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteEmployeeData(@RequestBody Employees employees) {
        employeesRepo.deleteById(employees.getId());
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Deleted Succesfully");
        return ResponseEntity.ok(responseInJson);
    }



}
