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

    private Connection conn = CarSharingDatabase.getConnection();

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
}
