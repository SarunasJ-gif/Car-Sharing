package com.sarunas.model;

public class Customer {

    private String name;
    private int rentedCar_id;

    public Customer(String name, int rentedCar_id) {
        this.name = name;
        this.rentedCar_id = rentedCar_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRentedCar_id() {
        return rentedCar_id;
    }

    public void setRentedCar_id(int rentedCar_id) {
        this.rentedCar_id = rentedCar_id;
    }
}
