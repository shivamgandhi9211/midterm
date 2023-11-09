package com.georgiancollege.test1;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = DBCreds.findUser();
    private static String pass = DBCreds.findPass();
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/" + user;

    public static ArrayList<Employee> getEmployeesdb(String clause) {
        ArrayList<Employee> employeesData = new ArrayList<>();

        String sql = "SELECT employee_id, first_name, last_name, address, city, province, phone " +
                "FROM midTermEmployee WHERE " + clause + ";";

        try (
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String province = resultSet.getString("province");
                String phone = resultSet.getString("phone");

                Employee employee = new Employee(employeeId, firstName, lastName, address, city, province, phone);
                employeesData.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeesData;
    }

}
