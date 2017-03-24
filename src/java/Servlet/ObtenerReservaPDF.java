/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Reserva;
import Modelo.Servicio;
import com.itextpdf.text.BaseColor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.util.Iterator;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pasito
 */
public class ObtenerReservaPDF extends HttpServlet {

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
        response.setContentType("application/pdf");
        Document documento = new Document(PageSize.A4);
        try {
            HttpSession session = request.getSession(true);
            OutputStream o = response.getOutputStream();
            PdfWriter.getInstance(documento, o);

            documento.open();
            Reserva rI = (Reserva) session.getAttribute("reservaI");
            String NombreIda = (String) session.getAttribute("NombreIda");
            String NombreVuelta = (String) session.getAttribute("NombreVuelta");
            int totalPasajeros = rI.getNadultos() + rI.getNninos() + rI.getNbebes();
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);

            documento.add(new Paragraph("VUELO DE IDA ( " + NombreIda + " - " + NombreVuelta + " )", fuente));
            documento.add(new Paragraph("Con código de vuelo: " + rI.getVueloIda().getCODVuelo()));
            documento.add(new Paragraph("Se realizará el : " + rI.getVueloIda().getFecha().toString() + " a las " + rI.getVueloIda().getHora_Salida()));
            documento.add(new Paragraph("Coste por pasajero " + rI.getVueloIda().getPrecio() + " € "));
            if (rI.getVueloVuelta() != null) {
                documento.add(new Paragraph("VUELO DE VUELTA ( " + NombreVuelta + " - " + NombreIda + " )", fuente));
                documento.add(new Paragraph("Con código de vuelo: " + rI.getVueloVuelta().getCODVuelo()));
                documento.add(new Paragraph("Se realizará el : " + rI.getVueloVuelta().getFecha().toString() + " a las " + rI.getVueloVuelta().getHora_Salida()));
                documento.add(new Paragraph("Coste por pasajero " + rI.getVueloVuelta().getPrecio() + " € "));
            }
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Total de pasajeros: " + totalPasajeros));
            int pasaj = 0;
            documento.add(new Paragraph("Pasajeros:", fuente));
            for (int idx = 0; idx < rI.getNadultos(); idx++) {
                String pasajero = rI.getPasajero(pasaj).getTratamiento() + " " + rI.getPasajero(pasaj).getNombre() + " " + rI.getPasajero(pasaj).getApellidos()
                        + " con " + rI.getPasajero(pasaj).getTipoIden() + " : " + rI.getPasajero(pasaj).getIdentificador();
                documento.add(new Paragraph(pasajero));
                documento.add(new Paragraph("Servicio de IDA:", fuente));
                Iterator<Servicio> itS = rI.getPasajero(pasaj).getServiciosIDA().iterator();
                List listado = new List();
                while (itS.hasNext()) {
                    Servicio se = itS.next();
                    if (se.getServicio().equals("Reservar asiento")) {
                        String fila = "" + rI.getPasajero(pasaj).getAsientoI().charAt(0);
                        String columna = "" + rI.getPasajero(pasaj).getAsientoI().charAt(2);
                        String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " €";
                        listado.add(new ListItem(servi));
                    } else {
                        String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " €";
                        listado.add(new ListItem(servi));
                    }
                }
                documento.add(listado);
                if (rI.getVueloVuelta() != null) {
                    documento.add(new Paragraph("Servicio de VUELTA:", fuente));
                    Iterator<Servicio> itV = rI.getPasajero(pasaj).getServiciosVUELTA().iterator();
                    List listadoV = new List();
                    while (itV.hasNext()) {
                        Servicio se = itV.next();
                        if (se.getServicio().equals("Reservar asiento")) {
                            String fila = "" + rI.getPasajero(pasaj).getAsientoV().charAt(0);
                            String columna = "" + rI.getPasajero(pasaj).getAsientoV().charAt(2);
                            String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " €";
                            listadoV.add(new ListItem(servi));
                        } else {
                            String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " €";
                            listadoV.add(new ListItem(servi));
                        }
                    }
                    documento.add(listadoV);
                }
                pasaj++;
            }
            for (int idx = 0; idx < rI.getNninos(); idx++) {
                int posTutor = Integer.parseInt(rI.getPasajero(pasaj).getID_tutor());
                String pasajero = rI.getPasajero(pasaj).getTratamiento()+ " " + rI.getPasajero(pasaj).getNombre() + " " + rI.getPasajero(pasaj).getApellidos()
                        + " con " + rI.getPasajero(pasaj).getTipoIden() + " : " + rI.getPasajero(pasaj).getIdentificador()
                        + " tiene como tutor en el viaje a " + rI.getPasajero(posTutor).getNombre() + " " + rI.getPasajero(posTutor).getApellidos();
                documento.add(new Paragraph(pasajero));
                documento.add(new Paragraph("Servicio de IDA:", fuente));
                Iterator<Servicio> itS = rI.getPasajero(pasaj).getServiciosIDA().iterator();
                List listado = new List();
                while (itS.hasNext()) {
                    Servicio se = itS.next();
                    if (se.getServicio().equals("Reservar asiento")) {
                        String fila = "" + rI.getPasajero(pasaj).getAsientoI().charAt(0);
                        String columna = "" + rI.getPasajero(pasaj).getAsientoI().charAt(2);
                        String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " €";
                        listado.add(new ListItem(servi));
                    } else {
                        String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " €";
                        listado.add(new ListItem(servi));
                    }
                }
                documento.add(listado);
                if (rI.getVueloVuelta() != null) {
                    documento.add(new Paragraph("Servicio de VUELTA:", fuente));
                    Iterator<Servicio> itV = rI.getPasajero(pasaj).getServiciosVUELTA().iterator();
                    List listadoV = new List();
                    while (itV.hasNext()) {
                        Servicio se = itV.next();
                        if (se.getServicio().equals("Reservar asiento")) {
                            String fila = "" + rI.getPasajero(pasaj).getAsientoV().charAt(0);
                            String columna = "" + rI.getPasajero(pasaj).getAsientoV().charAt(2);
                            String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " €";
                            listadoV.add(new ListItem(servi));
                        } else {
                            String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " €";
                            listadoV.add(new ListItem(servi));
                        }
                    }
                    documento.add(listadoV);
                }
                pasaj++;
            }
            for (int idx = 0; idx < rI.getNbebes(); idx++) {
                int posTutor = Integer.parseInt(rI.getPasajero(pasaj).getID_tutor());
                String pasajero = rI.getPasajero(pasaj).getNombre() + " " + rI.getPasajero(pasaj).getApellidos()
                        + " con " + rI.getPasajero(pasaj).getTipoIden() + " : " + rI.getPasajero(pasaj).getIdentificador()
                        + " tiene como tutor en el viaje a " + rI.getPasajero(posTutor).getNombre() + " " + rI.getPasajero(posTutor).getApellidos();
                documento.add(new Paragraph(pasajero));
                pasaj++;
            }

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("El coste total es de " + rI.getaPagar() + " €"));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph("Código de reserva: " + rI.getCOD_Reserva() + "."));


        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
        documento.close();
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
