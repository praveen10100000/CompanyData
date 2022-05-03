package com.company.data.companyData.service;

import com.company.data.companyData.entity.Company;
import com.company.data.companyData.exception.EmptyInputException;
import com.company.data.companyData.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo ;

    public String addCompanyData(Company company) throws EmptyInputException {
        if( company.getName().isEmpty() || company.getName().length() == 0 )
        {
            throw new EmptyInputException("601", "Input field is wrong");
        }
        companyRepo.save(company) ;
        return "Added" ;
    }
    public String Capitalize(String Address)
    {
        if(Address == null || Address.isEmpty()) {
            return Address;
        }

        return Address.substring(0, 1).toUpperCase() + Address.substring(1);
    }

}
