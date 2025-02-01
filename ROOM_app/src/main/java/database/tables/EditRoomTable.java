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
import mainClasses.room;

/**
 *
 * @author antig
 */
public class EditRoomTable {

    public void addRoomFromJSON(String json) throws ClassNotFoundException {
        room bt = jsonToRoom(json);
        createNewRoom(bt);
    }

    public room jsonToRoom(String json) {
        Gson gson = new Gson();
        room btest = gson.fromJson(json, room.class);
        return btest;
    }

    public String bookToJSON(room bt) {
        Gson gson = new Gson();
        String json = gson.toJson(bt, room.class);
        return json;
    }

    public ArrayList<room> databaseToRooms() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<room> rooms = new ArrayList<room>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM room");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                room room = gson.fromJson(json, room.class);
                rooms.add(room);
            }
            return rooms;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<room> databaseToRooms(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<room> rooms;
        rooms = new ArrayList<room>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM rooms WHERE type= '" + type + "'");

            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                room room = gson.fromJson(json, room.class);
                rooms.add(room);
            }
            return rooms;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updateRoom(String id_room, String url) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt;
        stmt = con.createStatement();
        room bt;
        bt = new room();

        String update;
        update = "UPDATE room SET url='" + url + "'" + "WHERE id_room = '" + id_room + "'";
        stmt.executeUpdate(update);
    }

    public void deleteRoom(String id_room) throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt;
            stmt = con.createStatement();
            String deleteQuery = "DELETE FROM room WHERE id_room='" + id_room + "'";
            stmt.executeUpdate(deleteQuery);
            stmt.close();
        }
    }

    public void createRoomTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt;
            stmt = con.createStatement();
            String sql = "CREATE TABLE room "
                    + "(id_room INTEGER(13) not NULL, "
                    + "room_name VARCHAR(500) not null,"
                    + "facilities VARCHAR(500)  not null, "
                    + "type VARCHAR(500)  not null, "
                    + "capacity INTEGER not null , "
                    + "address VARCHAR (500), "
                    + "floor INTEGER(13) not NULL, "
                    + "PRIMARY KEY ( id_room ))";
            stmt.execute(sql);
            stmt.close();
        }

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @param bt
     * @throws ClassNotFoundException
     */
    public void createNewRoom(room bt) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            try (Statement stmt = con.createStatement()) {
                String insertQuery = "INSERT INTO "
                        + " room (id_room, address, capacity, facilities, floor, room_name, type) "
                        + " VALUES ("
                        + "'" + bt.getId_room() + "',"
                        + "'" + bt.getAddress() + "',"
                        + "'" + bt.getCapacity() + "',"
                        + "'" + bt.getFacilities() + "',"
                        + "'" + bt.getFloor() + "',"
                        + "'" + bt.getRoom_name() + "',"
                        + "'" + bt.getType() + "'"
                        + ")";
                //stmt.execute(table);
                System.out.println(insertQuery);
                stmt.executeUpdate(insertQuery);
                System.out.println("# The room was successfully added in the database.");
                /* Get the member id from the database and set it to the member */
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditRoomTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
