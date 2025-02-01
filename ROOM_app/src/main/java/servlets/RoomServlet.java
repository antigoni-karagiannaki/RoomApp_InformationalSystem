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
public class RoomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String Json = Check_In_DataBase(request.getParameter("room_name"));

            if (Json != null) {
                response.getWriter().write(Json);
                response.setStatus(200);
            } else {
                response.setStatus(403);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println("Got an exception! hereeeeeeeeeeeee\n " + e.toString());
            System.err.println(e.getMessage());
            response.setStatus(403);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String Check_In_DataBase(String room_name) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM room WHERE room_name= '" + room_name + "'");
            rs.next();
            String json;
            json = null;
            System.out.println(rs);
            json = DB_Connection.getResultsToJSON(rs);

            if (json != null) {
                return json;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
