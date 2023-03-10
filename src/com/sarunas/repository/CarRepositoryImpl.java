package com.sarunas.repository;

import com.sarunas.CarSharingDatabase;
import com.sarunas.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private final Connection conn = CarSharingDatabase.getConnection();

    public CarRepositoryImpl() {}


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
        String sql = "SELECT ID, NAME, COMPANY_ID FROM CAR WHERE COMPANY_ID = ? AND ID NOT IN" +
                "(SELECT RENTED_CAR_ID FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(company_Id));
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
    public int findCar(String carName) {
        String sql = "SELECT * FROM CAR WHERE NAME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, carName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Car findCarById(String id) {
        String sql = "SELECT * FROM CAR WHERE ID = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
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
