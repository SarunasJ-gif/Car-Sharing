package com.sarunas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyControllerImpl implements CompanyController{

    private Connection conn = CarSharingDatabase.getConnection();

    public CompanyControllerImpl() {
    }

    public void save(Company company) {
        String sql = "INSERT INTO COMPANY (NAME) VALUES (?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, company.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAll() {
        List<Company> list = new ArrayList<Company>();
        String sql = "SELECT * FROM COMPANY";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Company(rs.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
