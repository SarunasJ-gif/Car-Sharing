package com.sarunas;

import com.sarunas.console.ManagerConsole;
import com.sarunas.controller.CarController;
import com.sarunas.controller.CarControllerImpl;
import com.sarunas.controller.CompanyController;
import com.sarunas.controller.CompanyControllerImpl;
import com.sarunas.service.Service;

public class Main {

    public static void main(String[] args) {
        CarSharingDatabase.createCompanyTable();
        CarSharingDatabase.createCarTable();
        CompanyController companyController = new CompanyControllerImpl();
        CarController carController = new CarControllerImpl();
        Service service = new Service(companyController, carController);
        ManagerConsole console = new ManagerConsole(service);
        console.consoleAction();

    }
}
