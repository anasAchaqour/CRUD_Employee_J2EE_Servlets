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
public class home extends HttpServlet {

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
            out.println("<title>Servlet home</title>");

            out.println("<script src=\"https://code.jquery.com/jquery-3.7.1.js\"></script>");
            out.println("<script src=\"https://cdn.datatables.net/2.0.0/js/dataTables.js\"></script>");
            out.println("<script src=\"https://cdn.datatables.net/buttons/3.0.0/js/dataTables.buttons.js\"></script>");
            out.println("<script src=\"https://cdn.datatables.net/buttons/3.0.0/js/buttons.dataTables.js\"></script>");
            out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js\"></script>");
            out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js\"></script>");
            out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js\"></script>");
            out.println("<script src=\"https://cdn.datatables.net/buttons/3.0.0/js/buttons.html5.min.js\"></script>");
            out.println("<script src=\"https://cdn.datatables.net/buttons/3.0.0/js/buttons.print.min.js\"></script>");
            
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.datatables.net/2.0.0/css/dataTables.dataTables.css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.datatables.net/buttons/3.0.0/css/buttons.dataTables.css\">");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='p-5'>");

            try {
                // Establish database connection
                Class.forName("oracle.jdbc.OracleDriver");
                // Retrieve employees from the database
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root")) {
                    // Retrieve employees from the database
                    String selectQuery = "SELECT id, nom, salaire FROM employee";
                    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Display employees in a table
                    out.println("<table id=\"example\" class=\"display nowrap\" style=\"width:100%\">");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>Id</th>");
                    out.println("<th>Nome</th>");
                    out.println("<th>Salaire</th>");
                    out.println("<th>Actions</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        double salaire = resultSet.getDouble("salaire");

                        out.println("<tr>");
                        out.println("<td>" + id + "</td>");
                        out.println("<td>" + nom + "</td>");
                        out.println("<td>" + salaire + "</td>");
                        out.println("<td>");
                        out.println("<a href='delete?id=" + id + "' class='btn btn-danger'>Delete</a>");
                        out.println("<a href='updatePage?id=" + id + "' class='btn btn-warning'>Update</a>");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("<tfoot>");
                    out.println("<tr>");
                    out.println("<th>Id</th>");
                    out.println("<th>Nome</th>");
                    out.println("<th>Salaire</th>");
                    out.println("<th>Actions</th>");
                    out.println("</tr>");
                    out.println("</tfoot>");
                    out.println("</table>");

                    // Close resources
                    resultSet.close();
                    preparedStatement.close();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                out.println("Error: " + e.getMessage());
            }

            out.println("</table>");
            
            out.println("<a href='addPage' class='btn btn-primary'>Add New Employee</a>");

            out.println("</div>");

            out.println("<script>");
            out.println("new DataTable('#example', {");
            out.println("    layout: {");
            out.println("        topStart: {");
            out.println("            buttons: ['copy', 'csv', 'excel', 'pdf', 'print']");
            out.println("        }");
            out.println("    }");
            out.println("});");
            out.println("</script>");
            
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
