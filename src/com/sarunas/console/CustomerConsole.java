package com.sarunas.console;

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
                System.out.println("> " + chooseCustomer);
                if ("0".equals(chooseCustomer)) {
                    customerListInAction = false;
                } else {
                    System.out.println("1. Rent a car ");
                    System.out.println("2. Return a rented car ");
                    System.out.println("3. My rented car ");
                    System.out.println("0. Back ");
                    String carActionChoice = scanner.nextLine();
                    System.out.println("> " + carActionChoice);
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
            System.out.println("The company list is empty! \n");
            return false;
        }
    }
}
