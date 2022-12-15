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
            System.out.println("> " + menu);
            if ("0".equals(menu)) {
                break;
            } else if ("1".equals(menu)) {
                while (true) {
                    System.out.println();
                    System.out.println("1. Company list ");
                    System.out.println("2. Create a company ");
                    System.out.println("0. Back ");
                    String choice = scanner.nextLine();
                    System.out.println("> " + choice);
                    System.out.println();
                    if ("0".equals(choice)) {
                        break;
                    } else if ("1".equals(choice)) {
                        printCompany();
                    } else if ("2".equals(choice)) {
                        System.out.println("Enter the company name:");
                        String companyName = scanner.nextLine();
                        System.out.println("> " + companyName);
                        System.out.println();
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
        if (!companies.isEmpty()) {
            int count = 1;
            for (Company company : companies) {
                System.out.println(count + ". " + company.getName());
                count++;
            }
        } else {
            System.out.println("The company list is empty!");
            System.out.println();
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
}
