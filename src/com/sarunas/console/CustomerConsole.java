package com.sarunas.console;

import com.sarunas.model.Car;
import com.sarunas.model.Company;
import com.sarunas.model.Customer;
import com.sarunas.service.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                String chosenCustomer = scanner.nextLine();
                System.out.println("> " + chosenCustomer + "\n");
                if ("0".equals(chosenCustomer)) {
                    customerListInAction = false;
                } else {
                    Customer customer = service.getCustomer(chosenCustomer);
                    if (customer != null) {
                        boolean rentList = true;
                        while (rentList) {
                            printRentList();
                            String carRentOption = scanner.nextLine();
                            System.out.println("> " + carRentOption + "\n");
                            if ("0".equals(carRentOption)) {
                                rentList = false;
                                customerListInAction = false;
                            } else if ("1".equals(carRentOption)) {
                                int rentedCarId = service.getRentedCarId(chosenCustomer);
                                if (rentedCarId != 0) {
                                    System.out.println("You've already rented a car! \n");
                                } else {
                                    boolean isCompany = printCompany();
                                    if (isCompany) {
                                        rentCar(chosenCustomer);
                                    } else {
                                        customerListInAction = false;
                                    }
                                }
                            } else if ("2".equals(carRentOption)) {
                                returnRentedCar(chosenCustomer);
                            } else if ("3".equals(carRentOption)) {
                                showRentedCar(chosenCustomer);
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

    public void rentCar(String chosenCustomer) {
        boolean rentCar = true;
        while (rentCar) {
            String chosenCompany = scanner.nextLine();
            System.out.println("> " + chosenCompany + "\n");
            Company company = service.getCompany(chosenCompany);
            if (company != null) {
                while (true) {
                    Map<Integer, String> carList = printCar(chosenCompany);
                    String chosenCarNr = scanner.nextLine();
                    System.out.println("> " + chosenCarNr + "\n");
                    String carName = carList.get(Integer.parseInt(chosenCarNr));
                    if (carName != null) {
                        int carId = service.getCar(carName);
                        service.updateCustomerRentedCarId(Integer.parseInt(chosenCustomer), carId);
                        System.out.println("You rented '" + carName + "' \n");
                        rentCar = false;
                        break;
                    }
                }
            } else {
                printCompany();
            }

        }
    }

    public void returnRentedCar(String chosenCustomer) {
        int rentedCarId = service.getRentedCarId(chosenCustomer);
        if (rentedCarId == 0) {
            System.out.println("You didn't rent a car! \n");
        } else {
            service.updateCustomerRentedCarId(Integer.parseInt(chosenCustomer), 0);
            System.out.println("You've returned a rented car! \n");
        }
    }

    public void showRentedCar(String chosenCustomer) {
        int rentedCarId = service.getRentedCarId(chosenCustomer);
        if (rentedCarId != 0) {
            System.out.println("Your rented car: ");
            Car car = service.getCarById(String.valueOf(rentedCarId));
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

    private Map<Integer, String> printCar(String companyId) {
        List<Car> cars = service.getAllCars(companyId);
        Map<Integer, String> carsList = new HashMap<Integer, String>();
        int count = 1;
        if (!cars.isEmpty()) {
            System.out.println("Choose a car: ");
            for (Car car : cars) {
                System.out.println(count + ". " + car.getName());
                carsList.put(count, car.getName());
                count++;
            }
        System.out.println("0. Back ");
        } else {
            System.out.println("The car list is empty! \n");
        }
        return carsList;
    }

    private boolean printCompany() {
        List<Company> companies = service.getAllCompanies();
        int count = 1;
        if (!companies.isEmpty()) {
            System.out.println("Choose a company: ");
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
}
