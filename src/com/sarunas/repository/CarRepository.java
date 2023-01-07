package com.sarunas.repository;

import com.sarunas.model.Car;

import java.util.List;

public interface CarRepository {
    void save(Car car);
    List<Car> findAllCars(String company_Id);
    int findCar(String carName);
    Car findCarById(String id);
}
