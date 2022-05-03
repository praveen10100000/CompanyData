package com.company.data.companyData.controller;

import com.company.data.companyData.customResponse.EmployeeTransactionCustomResponse;
import com.company.data.companyData.entity.Company;
import com.company.data.companyData.entity.Employees;
import com.company.data.companyData.entity.EmployeesTransaction;
import com.company.data.companyData.entity.ResponseInJson;
import com.company.data.companyData.repo.EmployeesRepo;
import com.company.data.companyData.repo.EmployeestransactionRepo;
import com.company.data.companyData.service.CompanyService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employeeTransaction")
public class EmployeeTransactionController {

    @Autowired
    private EmployeestransactionRepo employeestransactionRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeesRepo employeesRepo;

    @PostMapping("/")
    public ResponseEntity<?> addEmployeeTransactionData(@RequestBody EmployeeTransactionCustomResponse
                                                                employeeTransactionCustomRequest,
                                                        EmployeesTransaction employeesTransaction) {
        if (employeeTransactionCustomRequest.getTransactionType() == 1 || employeeTransactionCustomRequest.getTransactionType() == 2) {
            Employees employees1 = employeesRepo.getById(employeeTransactionCustomRequest.getEmployeeId());
            employeesTransaction.setAmount(employeeTransactionCustomRequest.getAmount());
            employeesTransaction.setDetails(employeeTransactionCustomRequest.getDetails());
            employeesTransaction.setTransactionType(employeeTransactionCustomRequest.getTransactionType());
            employeesTransaction.setEmployees(employees1);
            employeesTransaction.setDetails(companyService.Capitalize(employeeTransactionCustomRequest.getDetails()));
            employeestransactionRepo.save(employeesTransaction);
            ResponseInJson responseInJson = new ResponseInJson();
            responseInJson.setCode(200);
            responseInJson.setStatus("OK");
            responseInJson.setMessage("Added Successfully");
            responseInJson.setData(employees1);
            return ResponseEntity.ok(responseInJson);
        } else {
            ResponseInJson responseInJson = new ResponseInJson();
            responseInJson.setCode(400);
            responseInJson.setStatus("Wrong");
            responseInJson.setMessage("Please write valid transaction type");
            return ResponseEntity.ok(responseInJson);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getEmployeesTransactionData() {
        List<EmployeesTransaction> list = new ArrayList<>();
        employeestransactionRepo.findAll().forEach(empTrans -> list.add(empTrans));
        return ResponseEntity.ok(list);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateEmployeesTransactionData(@RequestBody EmployeesTransaction
                                                                    employeesTransaction) {
        if (employeesTransaction.getTransactionType() == 1 || employeesTransaction.getTransactionType() == 2) {
            EmployeesTransaction oldEmployeesTransaction = employeestransactionRepo.getById(employeesTransaction.getId());
            oldEmployeesTransaction.updateEmployeesTransactionData(employeesTransaction);
            employeesTransaction.setDetails(companyService.Capitalize(employeesTransaction.getDetails()));
            employeestransactionRepo.save(oldEmployeesTransaction);
            ResponseInJson responseInJson = new ResponseInJson();
            responseInJson.setCode(200);
            responseInJson.setStatus("OK");
            responseInJson.setMessage("Updates Successfully");
            return ResponseEntity.ok(responseInJson);
        } else {
            ResponseInJson responseInJson = new ResponseInJson();
            responseInJson.setCode(400);
            responseInJson.setStatus("Wrong");
            responseInJson.setMessage("Please write valid transaction type");
            return ResponseEntity.ok(responseInJson);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteEmployeeTransactionData(@RequestBody EmployeesTransaction employeesTransaction) {
        employeestransactionRepo.deleteById(employeesTransaction.getId());
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(200);
        responseInJson.setStatus("OK");
        responseInJson.setMessage("Deleted Successfully");
        return ResponseEntity.ok(responseInJson);
    }
}
