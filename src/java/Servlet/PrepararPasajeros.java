/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.Conexion;
import DAO.Operaciones;
import Modelo.Reserva;
import Modelo.Servicio;
import Modelo.Vuelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pasito
 */
public class PrepararPasajeros extends HttpServlet {

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
            ArrayList<Vuelo> vI = (ArrayList<Vuelo>) session.getAttribute("vuelosIda");
            String IDVueloIda = request.getParameter("IDA");
            int idVIDA = Integer.parseInt(IDVueloIda);

            Iterator<Vuelo> it1 = vI.iterator();
            while (it1.hasNext()) {
                Vuelo v = it1.next();
                if (v.getID() == idVIDA) {
                    rI.setVueloIda(v);
                }
            }

            if (session.getAttribute("vuelosVuelta") != null) {
                ArrayList<Vuelo> vV = (ArrayList<Vuelo>) session.getAttribute("vuelosVuelta");
                String IDVueloVuelta = request.getParameter("VUELTA");
                int idVVUELTA = Integer.parseInt(IDVueloVuelta);
                Iterator<Vuelo> it = vV.iterator();
                while (it.hasNext()) {
                    Vuelo v = it.next();
                    if (v.getID() == idVVUELTA) {
                        rI.setVueloVuelta(v);
                    }
                }
            }
            //Obtener servicios
            ArrayList<Servicio> servicios = new Operaciones().obtenerServicios(conexion);
            session.setAttribute("servicios", servicios);
            response.sendRedirect("Vistas/crearPasajeros.jsp");
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
