/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.Conexion;
import DAO.Operaciones;
import Modelo.Cliente;
import Modelo.Reserva;
import Modelo.Tarjeta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pasito
 */
public class montarCliente extends HttpServlet {

    private Connection conexion;

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

            HttpSession session = request.getSession(true);
            Reserva rI = (Reserva) session.getAttribute("reservaI");

            String tratamiento = request.getParameter("tratamiento");
            
            String nombre1 = request.getParameter("Nombre");
            byte[] arrByte = nombre1.getBytes("ISO-8859-1");
            String Nombre = new String(arrByte, "UTF-8");
            
            String apell = request.getParameter("Apell");
            byte[] arrByte1 = apell.getBytes("ISO-8859-1");
            String Apell = new String(arrByte1, "UTF-8");
            String tipoIden = request.getParameter("tipoIDEN");
            String dni = request.getParameter("dniU");
            String direccion = "";
            String tipoDir = request.getParameter("tipoDir");
            direccion += tipoDir + " ";
            String nombreCalle = request.getParameter("nombreCalle");
            direccion += nombreCalle + ", ";
            String numeroCalle = request.getParameter("numeroCalle");
            direccion += numeroCalle + ", ";
            String letraCalle = request.getParameter("letraCalle");
            if (letraCalle != null) {
                direccion += letraCalle + ", ";
            }
            String cp = request.getParameter("cp");
            direccion += cp + ", ";
            String localidad = request.getParameter("localidad");
            direccion += localidad + ", ";
            String provincia = request.getParameter("provincia");
            direccion += provincia + ", ";
            String pais = request.getParameter("pais");
            direccion += pais;
            String correoU = request.getParameter("correoU");
            String usuario = request.getParameter("usuario");
            String telf = request.getParameter("telf");
            String pass00 = request.getParameter("pass00");

            Cliente cliente = new Cliente(tratamiento, Nombre, Apell, tipoIden, dni, direccion, correoU, usuario, pass00, telf);
            rI.setCliente(cliente);
            
            session.setAttribute("usuario", usuario);
            ArrayList<Tarjeta> tarjetas = new Operaciones().obtenerTarjetas(conexion, usuario);
            session.setAttribute("tarjetas", tarjetas);
            response.sendRedirect("Vistas/eleccionTarjeta.jsp");

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

    @Override
    public void init() throws ServletException {
        /* Establecemos la conexión, si no existe */
        try {
            Conexion ConexBD = Conexion.getConexion();
            this.conexion = ConexBD.getConex();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
