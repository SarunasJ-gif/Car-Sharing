package com.sarunas.controller;

import com.sarunas.CarSharingDatabase;
import com.sarunas.controller.CarController;
import com.sarunas.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarControllerImpl implements CarController {

    private Connection conn = CarSharingDatabase.getConnection();

    public CarControllerImpl() {}


    @Override
    public void save(Car car) {
        String sql = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, car.getName());
            stmt.setInt(2, car.getCompany_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> findAllCars(String company_Id) {
        List<Car> cars = new ArrayList<Car>();
        String sql = "SELECT * FROM CAR WHERE COMPANY_ID = " + company_Id;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("NAME");
                int companyId = rs.getInt("COMPANY_ID");
                Car car = new Car(name, companyId);
                cars.add(car);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Car findCar(int id) {
        String sql = "SELECT * FROM CAR WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("NAME");
                int companyId = rs.getInt("COMPANY_ID");
                return new Car(name, companyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
