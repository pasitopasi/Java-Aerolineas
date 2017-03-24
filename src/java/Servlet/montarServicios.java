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
import Modelo.Tarjeta;
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
public class montarServicios extends HttpServlet {

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
            ArrayList<Servicio> s = (ArrayList<Servicio>) session.getAttribute("servicios");
            Reserva rI = (Reserva) session.getAttribute("reservaI");
            int pasajero = 0;
            for (int idx = 0; idx < rI.getNadultos(); idx++) {
                for (int i = 0; i < s.size(); i++) {
                    // Aqui montaremos los de IDA.
                    String posicionI = request.getParameter("servicioIDAAD" + idx + "-" + i);
                    if (posicionI != null) {
                        int posicionF = Integer.parseInt(posicionI);
                        if (s.get(posicionF).getServicio().equals("Reservar asiento")) {
                            String asiento = request.getParameter("AsientoAD" + idx + "" + i);
                            out.print(asiento + "<br>");
                            rI.getPasajero(pasajero).setAsientoI(asiento);
                        }
                        rI.getPasajero(pasajero).addServiciosIDA(s.get(posicionF));
                    }
                }
                if (rI.getVueloVuelta() != null) {
                    for (int i = 0; i < s.size(); i++) {
                        // Aqui montaremos los de vuelta.
                        String posicionV = request.getParameter("servicioVUELTAAD" + idx + "-" + i);
                        if (posicionV != null) {
                            int posicionF = Integer.parseInt(posicionV);
                            if (s.get(posicionF).getServicio().equals("Reservar asiento")) {
                                String asiento = request.getParameter("VAsientoAD" + idx + "" + i);
                                out.print(asiento + "<br>");
                                rI.getPasajero(pasajero).setAsientoV(asiento);
                            }
                            rI.getPasajero(pasajero).addServiciosVUELTA(s.get(posicionF));
                        }
                    }
                }
                pasajero++;
            }
            for (int idx = 0; idx < rI.getNninos(); idx++) {
                for (int i = 0; i < s.size(); i++) {
                    // Aqui montaremos los de IDA.
                    String posicionI = request.getParameter("servicioIDANI" + idx + "-" + i);
                    if (posicionI != null) {
                        int posicionF = Integer.parseInt(posicionI);
                        if (s.get(posicionF).getServicio().equals("Reservar asiento")) {
                            String asiento = request.getParameter("AsientoNI" + idx + "" + i);
                            out.print(asiento + "<br>");
                            rI.getPasajero(pasajero).setAsientoI(asiento);
                        }
                        rI.getPasajero(pasajero).addServiciosIDA(s.get(posicionF));
                    }
                }
                if (rI.getVueloVuelta() != null) {
                    for (int i = 0; i < s.size(); i++) {
                        // Aqui montaremos los de vuelta.
                        String posicionV = request.getParameter("servicioVUELTANI" + idx + "-" + i);
                        if (posicionV != null) {
                            int posicionF = Integer.parseInt(posicionV);
                            if (s.get(posicionF).getServicio().equals("Reservar asiento")) {
                                String asiento = request.getParameter("VAsientoNI" + idx + "" + i);
                                out.print(asiento + "<br>");
                                rI.getPasajero(pasajero).setAsientoV(asiento);
                            }
                            rI.getPasajero(pasajero).addServiciosVUELTA(s.get(posicionF));
                        }
                    }
                }
                pasajero++;
            }
            int pasaj = 0;
            out.print("VAMOS A VER TODOS LOS SERVICIOS:<br>");
            for (int idx = 0; idx < rI.getNadultos(); idx++) {
                out.print("<br>");
                out.print(rI.getPasajero(pasaj).toString());
                out.print("<br>");
                out.print("Servicios ida PASAJERO: " + pasaj);
                out.print("<br>");
                Iterator<Servicio> itS = rI.getPasajero(pasaj).getServiciosIDA().iterator();
                while (itS.hasNext()) {
                    Servicio se = itS.next();
                    out.print(se);
                    if (se.getServicio().equals("Reservar asiento")) {
                        out.print(" ASIENTO: " + rI.getPasajero(pasaj).getAsientoI());
                    }
                    out.print("<br>");
                }
                if (rI.getVueloVuelta() != null) {
                    out.print("servicios vuelta PASAJERO: " + pasaj);
                    out.print("<br>");
                    Iterator<Servicio> itV = rI.getPasajero(pasaj).getServiciosVUELTA().iterator();
                    while (itV.hasNext()) {
                        Servicio se = itV.next();
                        out.print(se);
                        if (se.getServicio().equals("Reservar asiento")) {
                            out.print(" ASIENTO: " + rI.getPasajero(pasaj).getAsientoV());
                        }
                        out.print("<br>");
                    }
                }
                pasaj++;
            }

            for (int idx = 0; idx < rI.getNninos(); idx++) {
                out.print("<br>");
                out.print(rI.getPasajero(pasaj).toString());
                out.print("<br>");
                out.print("Servicios ida PASAJERO: " + pasaj);
                out.print("<br>");
                Iterator<Servicio> itS = rI.getPasajero(pasaj).getServiciosIDA().iterator();
                while (itS.hasNext()) {
                    Servicio se = itS.next();
                    out.print(se);
                    if (se.getServicio().equals("Reservar asiento")) {
                        out.print(" ASIENTO: " + rI.getPasajero(pasaj).getAsientoI());
                    }
                    out.print("<br>");
                }
                if (rI.getVueloVuelta() != null) {
                    out.print("servicios vuelta PASAJERO: " + pasaj);
                    out.print("<br>");
                    Iterator<Servicio> itV = rI.getPasajero(pasaj).getServiciosVUELTA().iterator();
                    while (itV.hasNext()) {
                        Servicio se = itV.next();
                        out.print(se);
                        if (se.getServicio().equals("Reservar asiento")) {
                            out.print(" ASIENTO: " + rI.getPasajero(pasaj).getAsientoV());
                        }
                        out.print("<br>");
                    }
                }
                pasaj++;
            }
            //totalPagar
            String total = request.getParameter("totalPagar");
            out.print("total: "+total);
            double to = Double.parseDouble(total);
            rI.setaPagar(to);
            if (session.getAttribute("usuario") == null) {
                response.sendRedirect("Vistas/registrarUsuario.jsp");
            } else {
                String user = (String) session.getAttribute("usuario");
                ArrayList<Tarjeta> tarjetas = new Operaciones().obtenerTarjetas(conexion, user);
                session.setAttribute("tarjetas", tarjetas);
                
                response.sendRedirect("Vistas/eleccionTarjeta.jsp");
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
