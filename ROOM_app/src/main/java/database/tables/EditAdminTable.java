/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.administrator;

/**
 *
 * @author antig
 */
public class EditAdminTable {

    public void addAdministratorFromJSON(String json) throws ClassNotFoundException {
        administrator lib = jsonToAdministrator(json);
        addNewAdministrator(lib);
    }

    public administrator jsonToAdministrator(String json) {
        Gson gson = new Gson();

        administrator lib = gson.fromJson(json, administrator.class);
        return lib;
    }

    public String administratorToJSON(administrator lib) {
        Gson gson = new Gson();

        String json = gson.toJson(lib, administrator.class);
        return json;
    }

    public void updateAdministrator(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE administrator SET password='" + password + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void printAdministratorDetails(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM administrator WHERE username = '" + username + "' AND password='" + password + "'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public administrator databaseToAdministrator(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM administrator WHERE username = '" + username + "' AND password='" + password + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            administrator lib = gson.fromJson(json, administrator.class);
            return lib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<administrator> databaseToAdministrator() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<administrator> administrators = new ArrayList<administrator>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM administrators");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                administrator lib = gson.fromJson(json, administrator.class);
                administrators.add(lib);
            }
            return administrators;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createAdministratorTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE administrator"
                + "(id_a INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(200) not null unique,	"
                + "    password VARCHAR(32) not null,"
                + "    firstname VARCHAR(30) not null,"
                + "    lastname VARCHAR(30) not null,"
                + "    date_of_birth DATE not null,"
                + "    home_address VARCHAR(50) not null,"
                + "    telephone VARCHAR(14),"
                + "    IBAN VARCHAR(200),"
                + " PRIMARY KEY (id_a))";
        stmt.execute(query);
        stmt.close();
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewAdministrator(administrator adm) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " administrator (username,email,password,firstname,lastname,date_of_birth,,home_address,"
                    + "phone_number,IBAN)"
                    + " VALUES ("
                    + "'" + adm.getUsername() + "',"
                    + "'" + adm.getEmail() + "',"
                    + "'" + adm.getPassword() + "',"
                    + "'" + adm.getFirstname() + "',"
                    + "'" + adm.getLastname() + "',"
                    + "'" + adm.getDate_of_birth() + "',"
                    + "'" + adm.getHome_address() + "',"
                    + "'" + adm.getPhone_number() + "',"
                    + "'" + adm.getIBAN() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The ADMIN was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditAdminTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
