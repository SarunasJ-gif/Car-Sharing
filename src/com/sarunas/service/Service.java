package com.sarunas.service;

import com.sarunas.repository.CarRepository;
import com.sarunas.repository.CompanyRepository;
import com.sarunas.repository.CustomerRepository;
import com.sarunas.model.Car;
import com.sarunas.model.Company;
import com.sarunas.model.Customer;

import java.util.List;

public class Service {

    private final CompanyRepository companyRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public Service(CompanyRepository companyRepository,
                   CarRepository carRepository,
                   CustomerRepository customerRepository) {
        this.companyRepository = companyRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAllCompanies();
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompany(String id) {
        return companyRepository.findCompany(id);
    }

    public List<Car> getAllCars(String company_Id) {
        return carRepository.findAllCars(company_Id);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    public int getRentedCarId(String id) {
        return customerRepository.findRentedCarId(id);
    }

    public int getCar(String carName) {
        return carRepository.findCar(carName);
    }

    public Car getCarById(String id) {
        return carRepository.findCarById(id);
    }

    public void updateCustomerRentedCarId(int customerId, int rentedCarId) {
        customerRepository.updateRentedCarId(customerId, rentedCarId);
    }

    public Customer getCustomer(String id) {
        return customerRepository.findCustomer(id);
    }
}
