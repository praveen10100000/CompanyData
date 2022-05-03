package com.company.data.companyData.controller;

import com.company.data.companyData.entity.Company;
import com.company.data.companyData.entity.JsonFormate;
import com.company.data.companyData.entity.ResponseInJson;
import com.company.data.companyData.exception.DublicateValueException;
import com.company.data.companyData.exception.EmptyInputException;
import com.company.data.companyData.repo.CompanyRepo;
import com.company.data.companyData.service.CompanyService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies")
@Log

public class ComapanyController {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<?> addCompanyData(@Valid @RequestBody Company company) throws DublicateValueException {

        if (companyRepo.existsByContact(company.getContact()) || companyRepo.existsByName(company.getName())) {
            Map<String, String> map = new HashMap<>();
            if (companyRepo.existsByContact(company.getContact())) {
                map.put("contact", "already Exists");
            }
            if (companyRepo.existsByName(company.getName())) {
                map.put("name", "already Exists");
            }
            throw new DublicateValueException("Duplicate value", map);
        }
        company.setName(companyService.Capitalize(company.getName()));
        company.setAddress(companyService.Capitalize(company.getAddress()));
        Company comapny1 = companyRepo.save(company);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("Ok");
        responseInJson.setMessage("Added Successfully");
        JsonFormate jsonFormat = new JsonFormate();
        jsonFormat.setCompany(comapny1);
        responseInJson.setData(jsonFormat);
        return ResponseEntity.ok(responseInJson);
    }

    @GetMapping("/")
    public List<Company> getCompanyData() {
        List<Company> companies = new ArrayList<Company>();
        companyRepo.findAll().forEach(cmpny -> companies.add(cmpny));
        return companies;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompanyData(@PathVariable int id, @Valid @RequestBody Company company) {
        Company oldCompany = companyRepo.getById(id);
        oldCompany.updateCompanyData(company);
        company.setName(companyService.Capitalize(company.getName()));
        company.setAddress(companyService.Capitalize(company.getAddress()));
        companyRepo.save(oldCompany);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("Ok");
        responseInJson.setMessage("Updated Successfully");
        responseInJson.setData(oldCompany);
        return ResponseEntity.ok(responseInJson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompanyData(@PathVariable int id) {
        System.out.println(id);
        companyRepo.deleteById(id);
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Delete successfully");
        return ResponseEntity.ok(responseInJson);
    }


}
