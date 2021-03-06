/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.Conexion;
import DAO.Operaciones;
import Modelo.ConexionAreopuertos;
import Modelo.Reserva;
import Modelo.Vuelo;
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
public class ConseguirConexiones extends HttpServlet {

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

            String IDorigen = request.getParameter("selecOrigen");
            String IDdestino = request.getParameter("selecDestino");
            
            String TipoVuelo = request.getParameter("tipoVuelo");
            String adulto = request.getParameter("Nadulto");
            String nino = request.getParameter("Nnino");
            String bebe = request.getParameter("Nbebe");
            String fechaIda = request.getParameter("fechaIDAVUELT");
            String fechaVuelta = request.getParameter("fechaIDAVUELTA");

            int Nadulto = Integer.parseInt(adulto);
            int Nnino = Integer.parseInt(nino);
            int Nbebe = Integer.parseInt(bebe);

            if (TipoVuelo.equals("IDA")) {
                ConexionAreopuertos ida = new ConexionAreopuertos(IDorigen, IDdestino);
                ArrayList<Vuelo> vuelosIda = new Operaciones().obtenerVuelos(conexion, ida, fechaIda);
                if (vuelosIda != null) {
                    String NombreIda = new Operaciones().obtenerAeropuerto(conexion, IDorigen);
                    String NombreVuelta = new Operaciones().obtenerAeropuerto(conexion, IDdestino);
                    Reserva reservaI = new Reserva(Nadulto, Nnino, Nbebe);
                    session.setAttribute("NombreIda", NombreIda);
                    session.setAttribute("NombreVuelta", NombreVuelta);
                    session.setAttribute("reservaI", reservaI);
                    session.setAttribute("vuelosIda", vuelosIda);
                    //Eliminamos el objeto reservaV para evitar problemas.
                    session.removeAttribute("vuelosVuelta");
                }
                response.sendRedirect("Vistas/verViajes.jsp");
            }

            if (TipoVuelo.equals("IDA + VUELTA")) {
                ConexionAreopuertos ida = new ConexionAreopuertos(IDorigen, IDdestino);
                ConexionAreopuertos vuelta = new ConexionAreopuertos(IDdestino, IDorigen);
                ArrayList<Vuelo> vuelosIda = new Operaciones().obtenerVuelos(conexion, ida, fechaIda);
                ArrayList<Vuelo> vuelosvuelta = new Operaciones().obtenerVuelos(conexion, vuelta, fechaVuelta);
                if ((vuelosIda != null) && (vuelosvuelta != null)) {
                    String NombreIda = new Operaciones().obtenerAeropuerto(conexion, IDorigen);
                    String NombreVuelta = new Operaciones().obtenerAeropuerto(conexion, IDdestino);
                    Reserva reservaI = new Reserva(Nadulto, Nnino, Nbebe);
                    session.setAttribute("NombreIda", NombreIda);
                    session.setAttribute("NombreVuelta", NombreVuelta);
                    session.setAttribute("reservaI", reservaI);
                    session.setAttribute("vuelosIda", vuelosIda);
                    session.setAttribute("vuelosVuelta", vuelosvuelta);

                }
                response.sendRedirect("Vistas/verViajes.jsp");
            }
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
