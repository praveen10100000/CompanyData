package com.company.data.companyData.repo;

import com.company.data.companyData.entity.EmployeesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeestransactionRepo extends JpaRepository<EmployeesTransaction, Integer>, JpaSpecificationExecutor<EmployeesTransaction> {

}
