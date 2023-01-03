package com.sarunas;

public class Main {

    public static void main(String[] args) {
        CarSharingDatabase.createCompanyTable();
        CarSharingDatabase.createCarTable();
        CompanyController companyController = new CompanyControllerImpl();
        CarController carController = new CarControllerImpl();
        Service service = new Service(companyController, carController);
        Console console = new Console(service);
        console.consoleAction();

    }
}
