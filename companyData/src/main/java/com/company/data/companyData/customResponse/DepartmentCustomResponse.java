package com.company.data.companyData.customResponse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class DepartmentCustomResponse {
    @NotEmpty(message = "Please Enter name")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2}[a-z]*", message = "Please Enter Valid Name")
    String name;
    int companyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
