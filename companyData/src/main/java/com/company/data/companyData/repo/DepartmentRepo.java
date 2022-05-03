package com.company.data.companyData.repo;

import com.company.data.companyData.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

}
