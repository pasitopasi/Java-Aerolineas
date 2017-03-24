/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Pasajero;
import Modelo.Reserva;
import Modelo.Vuelo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class ObtenerFacturacionPDF extends HttpServlet {

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

        /* TODO output your page here. You may use following sample code. */
        Document documento = new Document(PageSize.A4);
        try {
            HttpSession session = request.getSession(true);
            OutputStream o = response.getOutputStream();
            PdfWriter.getInstance(documento, o);

            documento.open();
            Vuelo v = (Vuelo) session.getAttribute("vuelo");
            Reserva r = (Reserva) session.getAttribute("reservaF");
            Font fuente = new Font();
            fuente.setStyle(Font.BOLD);
            String qr = "";
            documento.add(new Paragraph("CÓDIGO DE VUELO:" + v.getCODVuelo(), fuente));
            qr += "CÓDIGO DE VUELO:" + v.getCODVuelo();
            documento.add(new Paragraph("Salida : " + v.getHora_Salida() + " del " + v.getFecha().toString().substring(8, 10) + " - " + v.getFecha().toString().substring(5, 7) + " - " + v.getFecha().toString().substring(0, 4)));
            qr += "\n" + "Salida : " + v.getHora_Salida() + " del " + v.getFecha().toString().substring(8, 10) + " - " + v.getFecha().toString().substring(5, 7) + " - " + v.getFecha().toString().substring(0, 4);
            documento.add(new Paragraph("PASAJEROS", fuente));
            Iterator<Pasajero> it = r.getPasajeros().iterator();
            while (it.hasNext()) {
                Pasajero p = it.next();
                if (!p.getTratamiento().equals("")) {
                    if (p.getNacionalidad().equals("ES")) {
                        documento.add(new Paragraph(p.sf() + " tiene nacionalidad española \n   Tiene el asiento " + p.getAsientoI()));
                        qr += "\n" + p.sf() + " tiene nacionalidad española, tiene el asiento " + p.getAsientoI();
                    } else {
                        documento.add(new Paragraph(p.sf() + " tiene otra nacionalidad diferente a la española \n   Tiene el asiento " + p.getAsientoI()));
                        qr += "\n" + p.sf() + " tiene nacionalidad extranjera, tiene el asiento " + p.getAsientoI();
                    }
                    try{
                        if (p.getServiciosIDA().get(0).getServicio() != null) {
                        qr += "\n TIENE PRIORIDAD DE EMBARQUE";
                    }
                    }catch(NullPointerException ex){
                        System.out.println(ex.getMessage());
                    }
                } else {
                    if (p.getNacionalidad().equals("ES")) {
                        documento.add(new Paragraph(p.sf() + " tiene nacionalidad española."));
                        qr += "\n" + p.sf() + " tiene nacionalidad española.";
                    } else {
                        documento.add(new Paragraph(p.sf() + " tiene nacionalidad extranjera."));
                        qr += "\n" + p.sf() + " tiene nacionalidad extranjera.";
                    }
                }
            }
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qr, 1000, 1000, null);
            Image codeQrImage = barcodeQRCode.getImage();
            codeQrImage.scaleAbsolute(100, 100);
            documento.add(codeQrImage);

        } catch (DocumentException ex) {
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
