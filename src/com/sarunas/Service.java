package com.sarunas;

import java.util.List;

public class Service {

    private final CompanyController companyController;
    private final CarController carController;

    public Service(CompanyController companyController, CarController carController) {
        this.companyController = companyController;
        this.carController = carController;
    }

    public List<Company> getAllCompanies() {
        return companyController.findAllCompanies();
    }

    public void saveCompany(Company company) {
        companyController.save(company);
    }

    public Company getCompany(String id) {
        return companyController.findCompany(id);
    }

    public List<Car> getAllCars(String company_Id) {
        return carController.findAllCars(company_Id);
    }

    public void saveCar(Car car) {
        carController.save(car);
    }
}
