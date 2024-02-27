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
public class updatePage extends HttpServlet {

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

        /* TODO output your page here. You may use following sample code. */
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html><head><title>Update Employee</title><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'></head><body>");
            out.println("<div class='p-5'>");
            out.println("<h2>Update Employee</h2>");
            // Retrieve employee ID from the request
            String employeeIdParam = request.getParameter("id");
            int employeeId = Integer.parseInt(employeeIdParam);

            try {
                // Establish database connection
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

                // Retrieve employee details from the database
                String selectQuery = "SELECT id, nom, salaire FROM employee WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, employeeId);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Display employee details in input fields
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    double salaire = resultSet.getDouble("salaire");

                    out.println("<form action='update' method='post'>");
                    out.println("<div class='form-group'>");
                    out.println("<label for='employeeId'>ID:</label>");
                    out.println("<input type='text' class='form-control' id='employeeId' name='employeeId' value='" + id + "' readonly>");
                    out.println("</div>");

                    out.println("<div class='form-group'>");
                    out.println("<label for='employeeNom'>Nom:</label>");
                    out.println("<input type='text' class='form-control' id='employeeNom' name='employeeNom' value='" + nom + "'>");
                    out.println("</div>");

                    out.println("<div class='form-group'>");
                    out.println("<label for='employeeSalaire'>Salaire:</label>");
                    out.println("<input type='text' class='form-control' id='employeeSalaire' name='employeeSalaire' value='" + salaire + "'>");
                    out.println("</div>");

                    out.println("<button type='submit' class='btn btn-primary'>Update Employee</button>");
                    out.println("</form>");
                    out.println("</div>");

                } else {
                    out.println("Employee not found.");
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

            } catch (ClassNotFoundException | SQLException e) {
                out.println("Error: " + e.getMessage());
            }

            out.println("</body></html>");
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
