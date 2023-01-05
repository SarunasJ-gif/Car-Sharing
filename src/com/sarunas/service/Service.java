package com.sarunas.service;

import com.sarunas.controller.CarController;
import com.sarunas.controller.CompanyController;
import com.sarunas.controller.CustomerController;
import com.sarunas.model.Car;
import com.sarunas.model.Company;
import com.sarunas.model.Customer;

import java.util.List;

public class Service {

    private final CompanyController companyController;
    private final CarController carController;
    private final CustomerController customerController;

    public Service(CompanyController companyController,
                   CarController carController,
                   CustomerController customerController) {
        this.companyController = companyController;
        this.carController = carController;
        this.customerController = customerController;
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

    public void saveCustomer(Customer customer) {
        customerController.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerController.findAllCustomers();
    }

}
