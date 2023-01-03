package com.sarunas;

import java.util.List;

public interface CarController {
    void save(Car car);
    List<Car> findAllCars(String company_Id);
}
