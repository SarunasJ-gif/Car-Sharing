package com.sarunas;

import java.util.List;

interface CompanyController {
    void save(Company company);
    List<Company> findAllCompanies();
    Company findCompany(String id);
}
