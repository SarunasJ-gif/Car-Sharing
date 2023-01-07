package com.sarunas.console;

import com.sarunas.model.Car;
import com.sarunas.model.Company;
import com.sarunas.model.Customer;
import com.sarunas.service.Service;

import java.util.List;
import java.util.Scanner;

public class CustomerConsole extends Console {

    private final Service service;
    private final Scanner scanner = new Scanner(System.in);

    public CustomerConsole(Service service) {
        this.service = service;
    }

    @Override
    public void consoleAction() {
        boolean customerListInAction = true;
        while (customerListInAction) {
            boolean isCustomer = printCustomers();
            if (isCustomer) {
                String chooseCustomer = scanner.nextLine();
                System.out.println("> " + chooseCustomer + "\n");
                if ("0".equals(chooseCustomer)) {
                    customerListInAction = false;
                } else {
                    boolean rentList = true;
                    while (rentList) {
                        printRentList();
                        String rentCarChoice = scanner.nextLine();
                        System.out.println("> " + rentCarChoice + "\n");
                        if ("0".equals(rentCarChoice)) {
                            rentList = false;
                            customerListInAction = false;
                        } else if ("1".equals(rentCarChoice)) {
                            // Rent a car
                        } else if ("2".equals(rentCarChoice)) {
                            // Return a rented car
                        } else if ("3".equals(rentCarChoice)) {
                            // 3. My rented car
                            int rentedCarId = service.getCarId(chooseCustomer);
                            if (rentedCarId != 0) {
                                System.out.println("Your rented car: ");
                                Car car = service.getCar(rentedCarId);
                                if (car != null) {
                                    Company company = service.getCompany(String.valueOf(car.getCompany_id()));
                                    if (company != null) {
                                        System.out.println(car.getName());
                                        System.out.println("Company: ");
                                        System.out.println(company.getName() + "\n");
                                    }
                                }
                            } else {
                                System.out.println("You didn't rent a car! \n");
                            }
                        }
                    }
                }
            } else {
                customerListInAction = false;
            }

        }
    }

    @Override
    public void createCustomer() {
        System.out.println("Enter the customer name: ");
        String customerName = scanner.nextLine();
        System.out.println("> " + customerName);
        service.saveCustomer(new Customer(customerName));
        System.out.println("The customer was added! \n");
    }

    private boolean printCustomers() {
        List<Customer> customers = service.getAllCustomers();
        int count = 1;
        if (!customers.isEmpty()) {
            System.out.println("Customer list: ");
            for (Customer customer : customers) {
                System.out.println(count + ". " + customer.getName());
                count++;
            }
            System.out.println("0. Back ");
            return true;
        } else {
            System.out.println("The customer list is empty! \n");
            return false;
        }
    }

    private void printRentList() {
        System.out.println("1. Rent a car ");
        System.out.println("2. Return a rented car ");
        System.out.println("3. My rented car ");
        System.out.println("0. Back ");
    }
}
