/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import mainClasses.employee;

/**
 *
 * @author antig
 */
public class RegistrationServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        JSON_Converter jc = new JSON_Converter();
//        response.setContentType("text/html;charset=UTF-8");
//        String Json = jc.getJSONFromAjax(request.getReader());
//
//        Object obj;
//        obj = new JsonParser().parse(Json);
//        JsonObject Jo = (JsonObject) obj;
//
//        int flag = 0, count = 0, check = 0;
//
//        try {
//            check = Check_In_DataBase("employee", "username", Jo.get("username").getAsString());
//
//            if (check == 1) {
//                response.getWriter().write("username ");
//                flag = 1;
//                response.setStatus(403);
//            }
//
//            count += check;
//            check = Check_In_DataBase("admin", "username", Jo.get("username").getAsString());
//
//            if ((check == 1) && (flag == 0)) {
//                response.getWriter().write("username ");
//                response.setStatus(403);
//            }
//
//            count += check;
//            check = Check_In_DataBase("employee", "email", Jo.get("email").getAsString());
//
//            if (check == 1) {
//                response.getWriter().write("email ");
//                flag = 1;
//                response.setStatus(403);
//            }
//
//            count += check;
//            check = Check_In_DataBase("admin", "email", Jo.get("email").getAsString());
//
//            if ((check == 1) && (flag == 0)) {
//                response.getWriter().write("email ");
//                response.setStatus(403);
//            }
//
//            count += check;
//            check = Check_In_DataBase("employee", "id_e", Jo.get("id_e").getAsString());
//
//            if (check == 1) {
//                response.getWriter().write("id_e");
//                response.setStatus(403);
//            }
//
//            count += check;
//
//        } catch (Exception e) {
//            System.err.println("Registration Servlet exception! ");
//            System.err.println(e.getMessage());
//            count = 1;
//            response.setStatus(403);
//        }
//
//        try {
//
//            if (count == 0) {
//
//                if (Jo.get("user_type").getAsString().equals("employee")) {
//
//                    EditEmployeeTable eut = new EditEmployeeTable();
//                    eut.addEmployeeFromJSON(Json);
//
//                } else {
//
//                    EditAdminTable elt = new EditAdminTable();
//                    elt.addAdministratorFromJSON(Json);
//
//                }
//
//                response.setStatus(200);
//
//            } else {
//
//                response.setStatus(403);
//
//            }
//
//        } catch (Exception e) {
//            System.err.println("Registration Servlet exception! ");
//            System.err.println(e.getMessage());
//            response.setStatus(403);
//        }
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public int Check_In_DataBase(String Where_to_check_from, String What_to_check, String value) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM " + Where_to_check_from + " WHERE " + What_to_check + " = '" + value + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            employee emp = gson.fromJson(json, employee.class);
            if (emp != null) {
                return 1;
            } else {
                return 0;
            }
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }
}
