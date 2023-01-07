package com.sarunas;

import com.sarunas.controller.MenuController;
import com.sarunas.controller.CustomerController;
import com.sarunas.controller.ManagerController;
import com.sarunas.repository.*;
import com.sarunas.service.Service;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CarSharingDatabase.createCompanyTable();
        CarSharingDatabase.createCarTable();
        CarSharingDatabase.createCustomerTable();
        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CarRepository carRepository = new CarRepositoryImpl();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        Service service = new Service(companyRepository, carRepository, customerRepository);
        while (true) {
            MenuController menuController = new MenuController();
            menuController.consoleAction();
            String menu = scanner.nextLine();
            System.out.println("> " + menu + "\n");
            if ("0".equals(menu)) {
                break;
            } else if ("1".equals(menu)) {
                menuController = new ManagerController(service);
                menuController.consoleAction();
            } else if ("2".equals(menu)) {
                menuController = new CustomerController(service);
                menuController.consoleAction();
            } else if ("3".equals(menu)) {
                menuController = new CustomerController(service);
                menuController.createCustomer();
            }
        }
    }
}
