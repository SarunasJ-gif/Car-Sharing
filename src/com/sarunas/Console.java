package com.sarunas;

import java.util.List;
import java.util.Scanner;

public class Console {

    private CompanyController controller;

    public Console(CompanyController controller) {
        this.controller = controller;
    }

    private Scanner scanner = new Scanner(System.in);
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
                        while (true) {
                            printCompany();
                            System.out.println();
                            String companyChoice = scanner.nextLine();
                            System.out.println("> " + companyChoice + "\n");
                            if ("0".equals(companyChoice)) {
                                break;
                            } else {
                                Company company = controller.getCompany(companyChoice);
                                if (company != null) {
                                    String name = company.getName();
                                    System.out.println("'" + name + "' company");
                                    while (true) {
                                        printCarChoice();
                                        String carOptionChoice = scanner.nextLine();
                                        System.out.println("> " + carOptionChoice + "\n");
                                        if ("0".equals(carOptionChoice)) {
                                            break;
                                        } else if ("1".equals(carOptionChoice)) {
                                            System.out.println("The car list is empty! \n");


                                        } else if ("2".equals(carOptionChoice)) {
                                            System.out.println("Enter the car name: ");
                                            String carName = scanner.nextLine();
                                            System.out.println("> " + carName);
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
                            controller.save(new Company(companyName));
                        } else {
                            System.out.println("The company was created!");
                        }
                    }
                }
            }
        }
    }


    private void printCompany() {
        List<Company> companies = controller.getAll();
        int count = 1;
        if (!companies.isEmpty()) {
            System.out.println("Choose the company: ");
            for (Company company : companies) {
                System.out.println(count + ". " + company.getName());
                count++;
            }
            System.out.println("0. Back ");
        } else {
            System.out.println("The company list is empty! \n");
        }
    }


    private boolean checkingCompanyName(String companyName) {
        List<Company> companies = controller.getAll();
        for (Company company : companies) {
            if (companyName.equals(company.getName())) {
                return false;
            }
        }
        return true;
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
