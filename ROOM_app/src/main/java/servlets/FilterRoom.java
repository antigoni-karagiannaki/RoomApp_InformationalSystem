/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DB_Connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antig
 */
public class FilterRoom extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("value");
        String search = request.getParameter("search");

        try {
            String json;
            json = null;
            json = Rooms_from_Database(value, search);
            if (json.length() == 0) {
                if (search.equals("capacity<='")) {
                    json = "capacity";
                } else if (search.equals("status='")) {
                    json = "status";
                } else if (search.equals("type='")) {
                    json = "type";
                }

            }
            response.getWriter().write(json);
            response.setStatus(200);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public String Rooms_from_Database(String value, String search) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT room.id_room, room.room_name, room.floor , room.capacity , room.address , room.type , room.facilities , room.id_r , room.status , room.price  FROM room LEFT JOIN reservation ON room.id_r=reservation.id_r WHERE " + search + value + "'");
            String json = new String();
            String tmp = new String();
            while (rs.next()) {

                tmp = DB_Connection.getResultsToJSON(rs);
                json += tmp;

            }
            return json;
        } catch (SQLException e) {
            System.err.println("Exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


}
