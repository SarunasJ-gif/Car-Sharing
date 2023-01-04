package com.sarunas.console;

import com.sarunas.model.Car;
import com.sarunas.model.Company;
import com.sarunas.service.Service;

import java.util.List;
import java.util.Scanner;

public class ManagerConsole {

    private final Service service;

    public ManagerConsole(Service service) {
        this.service = service;
    }

    private final Scanner scanner = new Scanner(System.in);
    public void consoleAction() {
        while (true) {
            System.out.println("1. Log in as a manager ");
            System.out.println("0. Exit ");
            String menu = scanner.nextLine();
            System.out.println("> " + menu + "\n");
            if ("0".equals(menu)) {
                break;
            } else if ("1".equals(menu)) {
                while (true) {
                    printCompanyChoice();
                    String choice = scanner.nextLine();
                    System.out.println("> " + choice + "\n");
                    if ("0".equals(choice)) {
                        break;
                    } else if ("1".equals(choice)) {
                        boolean isCompany = printCompany();
                        System.out.println();
                        boolean companyList = true;
                        while (companyList && isCompany) {
                            String companyChoice = scanner.nextLine();
                            System.out.println("> " + companyChoice + "\n");
                            if ("0".equals(companyChoice)) {
                                break;
                            } else {
                                Company company = service.getCompany(companyChoice);
                                if (company != null) {
                                    String name = company.getName();
                                    System.out.println("'" + name + "' company");
                                    while (true) {
                                        printCarChoice();
                                        String carOptionChoice = scanner.nextLine();
                                        System.out.println("> " + carOptionChoice + "\n");
                                        if ("0".equals(carOptionChoice)) {
                                            companyList = false;
                                            break;
                                        } else if ("1".equals(carOptionChoice)) {
                                            printCar(companyChoice);
                                            System.out.println();
                                        } else if ("2".equals(carOptionChoice)) {
                                            System.out.println("Enter the car name: ");
                                            String carName = scanner.nextLine();
                                            System.out.println("> " + carName);
                                            service.saveCar(new Car(carName, Integer.parseInt(companyChoice)));
                                            System.out.println("The car was added! \n");
                                        }
                                    }

                                }
                            }
                        }
                    } else if ("2".equals(choice)) {
                        System.out.println("Enter the company name:");
                        String companyName = scanner.nextLine();
                        System.out.println("> " + companyName + "\n");
                        if (checkingCompanyName(companyName)) {
                            service.saveCompany(new Company(companyName));
                        } else {
                            System.out.println("The company was created!");
                        }
                    }
                }
            }
        }
    }


    private boolean printCompany() {
        List<Company> companies = service.getAllCompanies();
        int count = 1;
        if (!companies.isEmpty()) {
            System.out.println("Choose the company: ");
            for (Company company : companies) {
                System.out.println(count + ". " + company.getName());
                count++;
            }
            System.out.println("0. Back ");
            return true;
        } else {
            System.out.println("The company list is empty! \n");
            return false;
        }
    }


    private boolean checkingCompanyName(String companyName) {
        List<Company> companies = service.getAllCompanies();
        for (Company company : companies) {
            if (companyName.equals(company.getName())) {
                return false;
            }
        }
        return true;
    }

    private void printCar(String companyId) {
        List<Car> cars = service.getAllCars(companyId);
        int count = 1;
        if (!cars.isEmpty()) {
            System.out.println("Car list: ");
            for (Car car : cars) {
                System.out.println(count + ". " + car.getName());
                count++;
            }
        } else {
            System.out.println("The car list is empty! \n");
        }
    }

    private void printCompanyChoice() {
        System.out.println("1. Company list ");
        System.out.println("2. Create a company ");
        System.out.println("0. Back ");
    }

    private void printCarChoice() {
        System.out.println("1. Car list ");
        System.out.println("2. Create a car ");
        System.out.println("0. Back ");
    }
}
