package com.sarunas;

import com.sarunas.console.Console;
import com.sarunas.console.CustomerConsole;
import com.sarunas.console.ManagerConsole;
import com.sarunas.controller.*;
import com.sarunas.service.Service;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CarSharingDatabase.createCompanyTable();
        CarSharingDatabase.createCarTable();
        CarSharingDatabase.createCustomerTable();
        CompanyController companyController = new CompanyControllerImpl();
        CarController carController = new CarControllerImpl();
        CustomerController customerController = new CustomerControllerImpl();
        Service service = new Service(companyController, carController, customerController);
        while (true) {
            Console console = new Console();
            console.consoleAction();
            String menu = scanner.nextLine();
            System.out.println("> " + menu + "\n");
            if ("0".equals(menu)) {
                break;
            } else if ("1".equals(menu)) {
                console = new ManagerConsole(service);
                console.consoleAction();
            } else if ("2".equals(menu)) {
                console = new CustomerConsole(service);
                console.consoleAction();
            } else if ("3".equals(menu)) {
                console = new CustomerConsole(service);
                console.createCustomer();
            }
        }
    }
}
