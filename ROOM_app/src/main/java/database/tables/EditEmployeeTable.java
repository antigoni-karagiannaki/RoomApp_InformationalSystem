/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.employee;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Functionalities provided : * add New Employee * print Eployee Details *
 * Return all Employees (db to Employee)
 *
 *
 * @author antig
 */
public class EditEmployeeTable {

    public void addEmployeeFromJSON(String json) throws ClassNotFoundException {
        employee user = jsonToEmployee(json);
        addNewEmployee(user);
    }

    public employee jsonToEmployee(String json) {
        Gson gson = new Gson();

        employee user = gson.fromJson(json, employee.class);
        return user;
    }

    public String EmployeeToJSON(employee user) {
        Gson gson = new Gson();

        String json = gson.toJson(user, employee.class);
        return json;
    }

    public void printEmployeeDetails(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM employee WHERE username = '" + username + "' AND password='" + password + "'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public employee databaseToEmployee(String username, String password) throws SQLException, ClassNotFoundException {
        System.err.println("099999009090 exception! ");

        Connection con = DB_Connection.getConnection();
        System.err.println("0υυυυυυυυ99009090 exception! ");
        Statement stmt = con.createStatement();
        System.err.println("ττττττττττττττ exception! ");

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM employee WHERE username = '" + username + "' AND password='" + password + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            employee user = gson.fromJson(json, employee.class);
            return user;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String databaseEmployeeToJSON(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM employee WHERE username = '" + username + "' AND password='" + password + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createEmployeeTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE employee"
                + "(id_e INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(200) not null unique,	"
                + "    password VARCHAR(32) not null,"
                + "    firstname VARCHAR(30) not null,"
                + "    lastname VARCHAR(30) not null,"
                + "    date_of_birth DATE not null,"
                + "    home_address VARCHAR(50) not null,"
                + "    phone_number VARCHAR(11),"
                + " PRIMARY KEY ( id_e))";
        stmt.execute(query);
        stmt.close();
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @param user
     * @throws ClassNotFoundException
     */
    public void addNewEmployee(employee user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " employee (username,email,password,firstname,lastname,date_of_birth,home_address,"
                    + "phone_number)"
                    + " VALUES ("
                    + "'" + user.getUsername() + "',"
                    + "'" + user.getEmail() + "',"
                    + "'" + user.getPassword() + "',"
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getDate_of_birth() + "',"
                    + "'" + user.getHome_address() + "',"
                    + "'" + user.getPhone_number() + "',"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The user(employee) was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditEmployeeTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Returns all Employees in the Database. */
    public ArrayList<employee> databaseToEmployee() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<employee> users = new ArrayList<employee>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                employee user = gson.fromJson(json, employee.class);
                users.add(user);
            }
            return users;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

}
