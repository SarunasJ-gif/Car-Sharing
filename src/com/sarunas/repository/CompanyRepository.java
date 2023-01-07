package com.sarunas.repository;

import com.sarunas.model.Company;

import java.util.List;

public interface CompanyRepository {
    void save(Company company);
    List<Company> findAllCompanies();
    Company findCompany(String id);
}
