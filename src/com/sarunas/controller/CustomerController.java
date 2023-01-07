package com.sarunas.controller;

import com.sarunas.model.Customer;

import java.util.List;

public interface CustomerController {
    void save(Customer customer);
    List<Customer> findAllCustomers();
    int findRentedCarId(String id);
    void updateRentedCarId(int customerId, int rentedCarId);
    Customer findCustomer(String id);
}
