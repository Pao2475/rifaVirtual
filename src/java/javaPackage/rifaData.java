/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaPackage;

import datos.PersonaJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USUARIO
 */
@WebServlet(urlPatterns = {"/rifaData"})
public class rifaData extends HttpServlet {

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
            datos.PersonaJDBC datos = new PersonaJDBC();
            String numeros="",numerosSele="";
            try {
                numeros = datos.select();
                numerosSele=datos.select2();
            } catch (Exception e) {
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <title>Rifa virtual</title>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("        <link href=\"codeCSS.css\" type=\"text/css\" rel=\"stylesheet\"/>");
            out.println("        <link rel=\"icon\" type=\"image/png\" href=\"\" />");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <div id=\"encabezado\">");
            out.println("        <h2>Rifa virtual</h2>   ");
            out.println("       <input type=\"text\"  id=\"numerosS\" style=\"display:none\" value="+numeros+">");
            out.println("       <input type=\"text\"  id=\"numerosSe\" style=\"display:none\" value="+numerosSele+">");
            out.println("        </div>");     
            out.println("        <div id=\"reg\">");
            out.println("            <form id=\"formulario\" name=\"formularioUsuario\" method=\"post\" ");
            out.println("                  action=\"/Rifa/rifaData\" ");
            out.println("                  onsubmit=\"return confirmar()\">");
            out.println("                <h2>Datos Usuario</h2>");
            out.println("                <input type=\"text\" name=\"txtNombre\" id=\"txtNombre\" placeholder=\"Nombre\" required/>");
            out.println("                <br>");
            out.println("                <input type=\"number\" name=\"txtCedula\" id=\"txtCedula\" placeholder=\"Cédula\" required/>");
            out.println("                <br>");
            out.println("                <input type=\"number\" name=\"txtTelefono\" id=\"txtTelefono\" placeholder=\"Telefono\" required/>");
            out.println("                <input type=\"text\" name=\"txtNumeros\" id=\"nums\" style=\"display:none\"/>");
            out.println("                <br>");
            out.println("                <input type=\"submit\" name=\"btnEnviar\" value=\"Quiero ir a Cancun\"/>");
            out.println("           </form>");
            out.println("        </div>");
            out.println("        <div id=\"conten\"><h2>Números</h2></div>   ");
            out.println("        <script src=\"codeJavaScript.js\"></script>");
            out.println("    </body>");
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
            throws ServletException, IOException 
    {
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
            throws ServletException, IOException 
    {
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            String cedula = request.getParameter("txtCedula");
            String numero = request.getParameter("txtNumeros");
            String nombre = request.getParameter("txtNombre");
            String telefono = request.getParameter("txtTelefono");
            
            String [] nums = numero.split(",");
            java.sql.Connection userConn=null;
            try {
                userConn = new datos.Conexion().getConnection();
                
                if (userConn.getAutoCommit()) 
                {
                    userConn.setAutoCommit(false);
                }
            
                datos.PersonaJDBC datos = new PersonaJDBC(userConn);

                for (int i = 0; i < nums.length; i++) 
                {
                    try {
                        datos.update(nums[i], nombre, cedula, telefono);
                    } catch (Exception e) {
                    }

                }
                userConn.commit();
            } catch (Exception e) 
            {
                try {
                    userConn.rollback();
                } catch (Exception r) {}
                
            }
            
            processRequest(request, response);
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
