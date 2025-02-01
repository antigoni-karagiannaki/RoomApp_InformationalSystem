/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditAdminTable;
import database.tables.EditEmployeeTable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.administrator;
import mainClasses.employee;

/**
 *
 * @author antig
 */
public class LOGINservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            EditEmployeeTable eut = new EditEmployeeTable();
            System.err.println("99990 exception! ");

            employee su = eut.databaseToEmployee(username, password);
            System.err.println("0 exception! ");
            if (su == null) {
                System.err.println("1 exception! ");
                EditAdminTable elt = new EditAdminTable();
                administrator li = null;
                //elt.databaseToAdministrator(username, password);
                if (li == null) {
                    response.setStatus(403);
                } else if (li != null) {

                    response.getWriter().write("employee");
                    response.setStatus(200);
                }

            } else if (su != null) {

                response.getWriter().write("employee");
                response.setStatus(200);
            }

        } catch (Exception e) {
            System.err.println("Log in Servlet exception! ");
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

}
