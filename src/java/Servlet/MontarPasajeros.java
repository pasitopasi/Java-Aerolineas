/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.Conexion;
import DAO.Operaciones;
import Modelo.Adulto;
import Modelo.Avion;
import Modelo.Bebe;
import Modelo.Niño;
import Modelo.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pasito
 */
public class MontarPasajeros extends HttpServlet {

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
            int i;
            for (i = 0; i < rI.getNadultos(); i++) {
                String tratamiento = request.getParameter("tratamientoAD" + i);
                
                String nombre1 = request.getParameter("NombreAD" + i);
                byte[] arrByte = nombre1.getBytes("ISO-8859-1");
                String nombre = new String(arrByte, "UTF-8");
                String apellido1 = request.getParameter("ApellAD" + i);
                byte[] arrByte1 = apellido1.getBytes("ISO-8859-1");
                String apellido = new String(arrByte1, "UTF-8");
                
                String tipoIden = request.getParameter("eleccionAD" + i);
                String identificador = request.getParameter("DNIAD" + i);
                String fechaCad = request.getParameter("fechaCadAD" + i);
                String fechaNac = request.getParameter("fechaNacAD" + i);
                String nacionalidad = request.getParameter("NacAD" + i);
                LocalDate localDatefechaCad = LocalDate.parse(fechaCad, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate localDatefechaNac = LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Adulto a = new Adulto(tratamiento, nombre, apellido, identificador, tipoIden, localDatefechaCad, nacionalidad, false, localDatefechaNac);
                rI.addPasajero(a);
            }
            for (i = 0; i < rI.getNninos(); i++) {
                String tratamiento1 = request.getParameter("tratemientoNI" + i);
                byte[] arrByte0 = tratamiento1.getBytes("ISO-8859-1");
                String tratamiento = new String(arrByte0, "UTF-8");
                
                String nombre1 = request.getParameter("NombreNI" + i);
                byte[] arrByte = nombre1.getBytes("ISO-8859-1");
                String nombre = new String(arrByte, "UTF-8");
                String apellido1 = request.getParameter("ApellNI" + i);
                byte[] arrByte1 = apellido1.getBytes("ISO-8859-1");
                String apellido = new String(arrByte1, "UTF-8");
                
                String tipoIden = request.getParameter("eleccionNI" + i);
                String identificador = request.getParameter("DNINI" + i);
                String fechaCad = request.getParameter("fechaCadNI" + i);
                String fechaNac = request.getParameter("fechaNacNI" + i);
                String nacionalidad = request.getParameter("NacNI" + i);
                String tutor = request.getParameter("tutorNI" + i);
                int ID = Integer.parseInt(tutor);
                rI.getPasajeros().get(ID).setTutor(true);
                LocalDate localDatefechaCad = LocalDate.parse(fechaCad, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate localDatefechaNac = LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Niño n = new Niño(tratamiento, nombre, apellido, identificador, tipoIden, localDatefechaCad, nacionalidad, tutor, localDatefechaNac);
                rI.addPasajero(n);
            }
            for (i = 0; i < rI.getNbebes(); i++) {
                String nombre1 = request.getParameter("NombreBE" + i);
                byte[] arrByte = nombre1.getBytes("ISO-8859-1");
                String nombre = new String(arrByte, "UTF-8");
                String apellido1 = request.getParameter("ApellBE" + i);
                byte[] arrByte1 = apellido1.getBytes("ISO-8859-1");
                String apellido = new String(arrByte1, "UTF-8");
                
                String tipoIden = request.getParameter("eleccionBE" + i);
                String identificador = request.getParameter("DNIBE" + i);
                String fechaCad = request.getParameter("fechaCadBE" + i);
                String fechaNac = request.getParameter("fechaNacBE" + i);
                String nacionalidad = request.getParameter("NacBE" + i);
                String tutor = request.getParameter("tutorBE" + i);
                int ID = Integer.parseInt(tutor);
                rI.getPasajeros().get(ID).setTutor(true);
                LocalDate localDatefechaCad = LocalDate.parse(fechaCad, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate localDatefechaNac = LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Bebe b = new Bebe(nombre, apellido, identificador, tipoIden, localDatefechaCad, nacionalidad, tutor, localDatefechaNac);
                rI.addPasajero(b);
            }
            rI.getVueloIda().getAvion();
            Avion avIDA = new Operaciones().ObtenerAvion(conexion, rI.getVueloIda().getAvion());
            session.setAttribute("avIDA", avIDA);
            session.removeAttribute("avVuelta");
            if (rI.getVueloVuelta() != null) {
                Avion avVuelta = new Operaciones().ObtenerAvion(conexion, rI.getVueloVuelta().getAvion());
                session.setAttribute("avVuelta", avVuelta);
            }
            response.sendRedirect("Vistas/eleccionServicios.jsp");
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
