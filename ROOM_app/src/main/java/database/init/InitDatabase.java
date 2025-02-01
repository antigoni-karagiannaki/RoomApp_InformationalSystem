/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import static database.DB_Connection.getInitialConnection;
import database.tables.EditAdminTable;
import database.tables.EditEmployeeTable;
import database.tables.EditRoomTable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mainClasses.administrator;
import mainClasses.employee;
import mainClasses.room;

/**
 *
 * @author antig
 */
public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InitDatabase init = new InitDatabase();
        init.initDatabase();
        init.addToDatabaseExamples();
        //init.dropDatabase();
    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE hy351_a");
        stmt.close();
        conn.close();
        initTables();
    }

    public void dropDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP DATABASE hy351_a");
        stmt.close();
        conn.close();
    }

    public void initTables() throws SQLException, ClassNotFoundException {
        EditEmployeeTable eut = new EditEmployeeTable();
        eut.createEmployeeTable();

        EditAdminTable edt = new EditAdminTable();
        edt.createAdministratorTable();

    }

    public void addToDatabaseExamples() throws ClassNotFoundException, SQLException {
        //Users

        EditEmployeeTable eut = new EditEmployeeTable();
        eut.addEmployeeFromJSON(Resources.employee1);
        eut.addEmployeeFromJSON(Resources.employee2);
        eut.addEmployeeFromJSON(Resources.employee3);

        //  admin
        EditAdminTable edt = new EditAdminTable();
        edt.addAdministratorFromJSON(Resources.admin);

        //Book
        EditRoomTable ebt = new EditRoomTable();

        ebt.addRoomFromJSON(Resources.room1);
        ebt.addRoomFromJSON(Resources.room2);
        ebt.addRoomFromJSON(Resources.room3);
        ebt.addRoomFromJSON(Resources.room4);
        ebt.addRoomFromJSON(Resources.room5);

    }

    public void databaseToJSON() throws ClassNotFoundException, SQLException {
        //Users
        EditEmployeeTable eut = new EditEmployeeTable();
        employee su = eut.databaseToEmployee("andria33", "andria12");
        String json = eut.EmployeeToJSON(su);
        System.out.println("employee\n" + json + "\n");

        //All the admin
        EditAdminTable elt = new EditAdminTable();
        ArrayList<administrator> libs = elt.databaseToAdministrator();
        Gson gson1 = new Gson();
        JsonArray jsonlibs = gson1.toJsonTree(libs).getAsJsonArray();
        System.out.println("admin\n" + jsonlibs + "\n");

        //All the Rooms
        EditRoomTable edt = new EditRoomTable();
        ArrayList<room> rooms = edt.databaseToRooms();
        Gson gson2 = new Gson();
        JsonArray jsonDoc = gson2.toJsonTree(rooms).getAsJsonArray();
        System.out.println("rooks\n" + jsonDoc + "\n");
    }
}
