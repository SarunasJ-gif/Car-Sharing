package com.sarunas;

public class Car {

    private String name;
    private int company_id;

    public Car(String name, int company_id) {
        this.name = name;
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
