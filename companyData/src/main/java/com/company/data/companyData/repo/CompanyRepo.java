package com.company.data.companyData.repo;

import com.company.data.companyData.entity.Company;
import jdk.dynalink.beans.StaticClass;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    boolean existsByContact(String contact);

    boolean existsByName(String name);

    boolean existsByNameAndContact(String name, String Contact);


}

