package com.sarunas.controller;

import com.sarunas.CarSharingDatabase;
import com.sarunas.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerControllerImpl implements CustomerController {

    private final Connection conn = CarSharingDatabase.getConnection();

    public CustomerControllerImpl() {
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (NAME) VALUES (?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        String sql = "SELECT * FROM CUSTOMER";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(rs.getString("NAME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public int findRentedCarId(String id) {
        String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = " + id;
        int rentedCarId = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rentedCarId = rs.getInt("RENTED_CAR_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentedCarId;
    }

    @Override
    public void updateRentedCarId(int customerId, int rentedCarId) {
        try {
            PreparedStatement stmt;
            if (rentedCarId != 0) {
                stmt = conn.prepareStatement(
                        "UPDATE CUSTOMER SET RENTED_CAR_ID = ? WHERE ID = ?");
                stmt.setInt(1, rentedCarId);
                stmt.setInt(2, customerId);
            } else {
                stmt = conn.prepareStatement(
                        "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = ?");
                stmt.setInt(1, customerId);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findCustomer(String id) {
        String sql = "SELECT * FROM CUSTOMER WHERE ID = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("NAME");
                int rentedCarId = rs.getInt("RENTED_CAR_ID");
                return new Customer(name, rentedCarId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
