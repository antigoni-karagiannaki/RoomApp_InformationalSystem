/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.reservation;

/**
 *
 * @author antig
 */
public class EditReservationTable {

    public void addReservationFromJSON(String json) throws ClassNotFoundException {
        reservation r = jsonToReservation(json);
        createNewReservation(r);
    }

    public reservation databaseToReservation(int id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM reservation WHERE id_r= '" + id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            reservation bt = gson.fromJson(json, reservation.class);
            return bt;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public reservation jsonToReservation(String json) {
        Gson gson = new Gson();
        reservation r = gson.fromJson(json, reservation.class);
        return r;
    }

    public String ReservationToJSON(reservation r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, reservation.class);
        return json;
    }

    public void updateReservation(int id_r, int id_e, String facilities) throws SQLException, ClassNotFoundException {
        Connection con;
        con = DB_Connection.getConnection();
        Statement stmt;
        stmt = con.createStatement();
        String updateQuery = "UPDATE reservation SET extra_facilities='" + facilities + "'";//...

        stmt.executeUpdate(updateQuery);
        stmt.close();
        con.close();
    }

    public void deleteReservation(int id_r) throws SQLException, ClassNotFoundException {
        Connection con;
        con = DB_Connection.getConnection();
        try (Statement stmt = con.createStatement()) {
            String deleteQuery = "DELETE FROM reservation WHERE id_r='" + id_r + "'";
            stmt.executeUpdate(deleteQuery);
        }
        con.close();
    }

    public void createReservationTable() throws SQLException, ClassNotFoundException {
        Connection con;
        con = DB_Connection.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sql = "CREATE TABLE reservation "
                    + "(id_r INTEGER(15) not NULL AUTO_INCREMENT, "
                    + " id_e INTEGER(15) not NULL, "
                    + " price FLOAT not NULL, "
                    + " id_a INTEGER(15) not NULL, "
                    + " reservation_date DATE not NULL, "
                    + " application_date DATE not NULL, "
                    + " time VARCHAR(15) not NULL, "
                    + " payment_methods VARCHAR(15) not NULL, "
                    + " extra_facilities VARCHAR(30) not NULL, "
                    + "FOREIGN KEY (id_a ) REFERENCES administrator(id_a ), "
                    + "FOREIGN KEY (id_e ) REFERENCES employee(id_e), "
                    + " PRIMARY KEY (id_r))";
            stmt.execute(sql);
        }
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @param bor
     * @throws ClassNotFoundException
     */
    public void createNewReservation(reservation bor) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt;
            stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " reservation (extra_facilities,price,id_r,time,reservation_date,	application_date ,payment_method )"
                    + " VALUES ("
                    + "'" + bor.getId_r() + "',"
                    + "'" + bor.getExtra_facilities() + "',"
                    + "'" + bor.getPrice() + "',"
                    + "'" + bor.getReservation_date() + "',"
                    + "'" + bor.getApplication_date() + "',"
                    + "'" + bor.getTime() + "',"
                    + "'" + bor.getPayment_method() + "'"
                    + ")";
            //stmt.execute(table);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The Reservation was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditReservationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
