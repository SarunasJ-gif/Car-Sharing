package com.sarunas.controller;

import com.sarunas.model.Company;

import java.util.List;

public interface CompanyController {
    void save(Company company);
    List<Company> findAllCompanies();
    Company findCompany(String id);
}
