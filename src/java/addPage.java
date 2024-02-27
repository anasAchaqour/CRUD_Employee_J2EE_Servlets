/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author SamCro
 */
public class addPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addPage</title>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='p-5'>");
            out.println("<h2>Add New Employee</h2>");

            out.println("<form action='addPage' method='post'>");
            out.println("<div class='form-group'>");
            out.println("<label for='employeeId'>ID:</label>");
            out.println("<input type='text' class='form-control' id='employeeId' name='employeeId'>");
            out.println("</div>");

            out.println("<div class='form-group'>");
            out.println("<label for='employeeNom'>Nom:</label>");
            out.println("<input type='text' class='form-control' id='employeeNom' name='employeeNom'>");
            out.println("</div>");

            out.println("<div class='form-group'>");
            out.println("<label for='employeeSalaire'>Salaire:</label>");
            out.println("<input type='text' class='form-control' id='employeeSalaire' name='employeeSalaire'>");
            out.println("</div>");

            out.println("<button type='submit' class='btn btn-primary'>Add Employee</button>");
            out.println("<a href='home' class='btn btn-warning'>Go to Home Page</a>");
            out.println("</form>");
            out.println("</ div>");

            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String employeeId = request.getParameter("employeeId");
        String employeeNom = request.getParameter("employeeNom");
        String employeeSalaire = request.getParameter("employeeSalaire");

        try {
            // Establish database connection
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

            // Prepare SQL statement
            String insertQuery = "INSERT INTO employee (id, nom, salaire) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, employeeId);
            preparedStatement.setString(2, employeeNom);
            preparedStatement.setString(3, employeeSalaire);

            // Execute the update
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
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
