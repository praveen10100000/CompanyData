package com.company.data.companyData.controller;

import com.company.data.companyData.customResponse.DepartmentCustomResponse;
import com.company.data.companyData.entity.Company;
import com.company.data.companyData.entity.Department;

import com.company.data.companyData.entity.ResponseInJson;
import com.company.data.companyData.repo.CompanyRepo;
import com.company.data.companyData.repo.DepartmentRepo;
import com.company.data.companyData.service.CompanyService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentRepo departmentRepo;

    @PostMapping("/")
    public ResponseEntity<?> addDepartmentData(@RequestBody DepartmentCustomResponse departmentCustomResponse) {
        Department department = new Department();
        Company company1 = companyRepo.getById(departmentCustomResponse.getCompanyId());
        department.setName(departmentCustomResponse.getName());
        department.setCompany(company1);
        department.setName(companyService.Capitalize(departmentCustomResponse.getName()));
        departmentRepo.save(department);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Added Successfully");
        responseInJson.setData(company1);
        return ResponseEntity.ok(responseInJson);
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getDepartmentData() {
        List<Department> list = new ArrayList<>();
        departmentRepo.findAll().forEach(depart -> list.add(depart));
        return ResponseEntity.ok(list);
    }

    @PutMapping("/")
    public ResponseEntity<ResponseInJson> updateDepartmentdata(@RequestBody Department department) {
        Department oldDepartment = departmentRepo.getById(department.getId());
        oldDepartment.updateDepartmentData(department);
        department.setName(companyService.Capitalize(department.getName()));
        departmentRepo.save(oldDepartment);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Updated Successfully");
        responseInJson.setData(oldDepartment);
        return ResponseEntity.ok(responseInJson);
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseInJson> deleteDepartmentData(@RequestBody Department department) {
        departmentRepo.deleteById(department.getId());
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Delete Successfully");
        return ResponseEntity.ok(responseInJson);
    }
}
