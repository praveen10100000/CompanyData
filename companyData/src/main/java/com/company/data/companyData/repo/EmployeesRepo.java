package com.company.data.companyData.repo;

import com.company.data.companyData.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Integer>, JpaSpecificationExecutor<Employees> {

}
