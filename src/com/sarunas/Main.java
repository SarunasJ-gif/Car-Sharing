package com.sarunas;

public class Main {

    public static void main(String[] args) {
        CarSharingDatabase.createCompanyTable();
        CarSharingDatabase.createCarTable();
        CompanyController controller = new CompanyControllerImpl();
        Console console = new Console(controller);
        console.consoleAction();

    }
}
