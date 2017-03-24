<%-- 
    Document   : eleccionServicios
    Created on : 18-ene-2017, 19:01:46
    Author     : pasito
--%>
<%@page import="Modelo.Avion"%>
<%@page import="Modelo.Servicio"%>
<%@page import="Modelo.Reserva"%>
<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : index
    Created on : 03-ene-2017, 11:48:04
    Author     : pasito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="pragma" content="no-cache"> 
        <meta http-equiv="cache-control" content="no-cache"> 
        <meta http-equiv="expires" content="0"> 
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- No deja volver a esta pagina dandole al boton de atras del navegador -->
        <script type="text/javascript">
            if (history.forward(1)) {
                location.replace(history.forward(1));
            }
        </script>
        <!-- fin  -->
        <link rel="stylesheet" type="text/css" href="../CSS/estilo.css" title="style" />
        <script type="text/javascript" src="../JavaScript/java.js">
        </script>
        <title>Reserva</title>
    </head>
    <% Reserva rI = (Reserva) session.getAttribute("reservaI"); %>
    <body onload="ObtenerFilasIDA(<% out.print(rI.getVueloIda().getID()); %>);
          <% if (rI.getVueloVuelta() != null) {
          %> ObtenerFilasV(<% out.print(rI.getVueloVuelta().getID()); %>); <%
              }
          %>">
        <div id="menu">
            <a href="../index.jsp"><img id="portada" src="../Imagenes/logo.png" alt="" ></a>
            <ul>
                <li><p><a href="buscarViaje.jsp">RESERVA</a></p></li>
                <li class="listaM"><p><a href="Facturar.jsp">FACTURAR</a></p></li>
                <li class="listaM"><p><a href="aterrizar.jsp">ATERRIZAR</a></p></li>
<li class="listaM"><p><a href="misReservas.jsp">ESTADISTICAS</a></p></li>
                            <% if (session.getAttribute("usuario") == null) { %>
                <li id="acceso"><p><a href="" ><p id="prueba">Acceder</p></a></p>
                    <ul id="cajon">
                        <form method="post" action="javascript: ObtenerUser();">
                            <li>Correo / Usuario:<input type="text" id="correo" name="correo" required></li>
                            <li>Contraseña:<input type="password" id="pass" name="pass" required></li>
                            <li>
                                <p><input name="control" type="submit" value="Acceder">
                                    <a href="../DispatcherAcceso?control=Registrar">Registrar</a></p>
                            </li>
                        </form>
                    </ul>
                </li>         
                <%
                } else { %>
                <li id="acceso"><p><a href=""><p id="prueba"><% out.print(session.getAttribute("usuario")); %></p></a></p>
                    <ul id="cajon">
                        <li>
                            <p><a href="../DispatcherAcceso?control=Desconectar">Desconectar</a></p>
                        </li>
                    </ul>
                </li> 
                <% }%>
            </ul>
        </div>
        <h1 id="titulo">4. Servicios</h1>
        <div id="titulo04"></div>
        <form id="formula" name="form1" method="post" action="../montarServicios" onload="this.form.reset();">
            <div id="superServicios">
                <div id="servicios">
                    <div id="pasajerosS">
                        <%
                            response.addHeader("Expires:", "Mon, 01 Jan 2001 00:00:00 GMT");
                            response.addHeader("Pragma", "no-cache");
                            response.addHeader("Cache-Control", "no-cache, must-revalidate");
                            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
                            response.addHeader("Cache-Control", "private");
                            String NombreIda = (String) session.getAttribute("NombreIda");
                            String NombreVuelta = (String) session.getAttribute("NombreVuelta");

                            ArrayList<Servicio> s = (ArrayList<Servicio>) session.getAttribute("servicios");
                            int pasajero = 0;
                            for (int idx = 0; idx < rI.getNadultos(); idx++) {
                        %>
                        <div class="botonMostrar" onclick="div('divAD<% out.print(idx); %>');">Mostrar/Ocultar Adulto <% out.print(idx + 1); %></div>
                        <div class="pasaj" id="divAD<% out.print(idx); %>" style="display: none;">
                            <span><% out.print(rI.getPasajero(pasajero).getTratamiento()); %></span>
                            <span><% out.print(rI.getPasajero(pasajero).getNombre()); %></span>
                            <span><% out.print(rI.getPasajero(pasajero).getApellidos()); %></span>
                            <span>(<% out.print(rI.getPasajero(pasajero).getTipoIden().toUpperCase()); %>: </span>
                            <span><% out.print(rI.getPasajero(pasajero).getIdentificador()); %>) escoja sus servicios:</span><br><br>
                            <div id="serviciosIDA">
                                <h3>Servicios IDA:</h3>
                                <% for (int i = 0; i < s.size(); i++) {
                                        if (s.get(i).getServicio().equals("Reservar asiento")) {%>
                                <input onchange="cambioPrecios('<% out.print(s.get(i).getServicio()); %>', <% out.print(s.get(i).getPrecio()); %>, this.id);" onclick="if (this.checked) {
                                            document.getElementById('eje').click();
                                            ponerAsiento('AsientoAD<% out.print(idx + "" + i); %>');
                                        } else {
                                            AsientoAD<% out.print(idx + "" + i); %>.value = '';
                                            AsientoAD<% out.print(idx + "" + i); %>.innerHTML = '';
                                        }
                                        ;" type="checkbox" id="servicioIDAAD<% out.print(idx + "-" + i); %>" name="servicioIDAAD<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio()); %>
                                <input type="text" id="AsientoAD<% out.print(idx + "" + i); %>" name="AsientoAD<% out.print(idx + "" + i); %>" size="1" readonly><br>
                                <%} else {
                                    if (!s.get(i).getServicio().equals("Bebe")) {%>
                                <input onchange="cambioPrecios('<% out.print(s.get(i).getServicio()); %>', <% out.print(s.get(i).getPrecio()); %>, this.id);" type="checkbox" id="servicioIDAAD<% out.print(idx + "-" + i); %>" name="servicioIDAAD<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio() + "<br>");
                                } else {
                                    if (rI.getPasajero(pasajero).isTutor()) {%>
                                <input checked onchange="this.checked = true;" type="checkbox" id="servicioIDAAD<% out.print(idx + "-" + i); %>" name="servicioIDAAD<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>"> <% out.print(s.get(i).getServicio() + " (Es tutor)<br>"); %>
                                <%}
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <% if (rI.getVueloVuelta() != null) {%>
                            <div id="serviciosVUELTA">
                                <h3>Servicios VUELTA:</h3>
                                <% for (int i = 0; i < s.size(); i++) {
                                        if (s.get(i).getServicio().equals("Reservar asiento")) {%>
                                <input onchange="cambioPreciosV('<% out.print(s.get(i).getServicio()); %> (V)', <% out.print(s.get(i).getPrecio()); %>, this.id);" onclick="if (this.checked) {
                                            document.getElementById('ejeV').click();
                                            ponerAsientoV('VAsientoAD<% out.print(idx + "" + i); %>');
                                        } else {
                                            VAsientoAD<% out.print(idx + "" + i); %>.value = '';
                                            VAsientoAD<% out.print(idx + "" + i); %>.innerHTML = '';
                                        }
                                        ;" type="checkbox" id="servicioVUELTAAD<% out.print(idx + "-" + i); %>" name="servicioVUELTAAD<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio()); %>
                                <input type="text" id="VAsientoAD<% out.print(idx + "" + i); %>" name="VAsientoAD<% out.print(idx + "" + i); %>" size="1" readonly><br>
                                <%} else {
                                    if (!s.get(i).getServicio().equals("Bebe")) {%>
                                <input onchange="cambioPreciosV('<% out.print(s.get(i).getServicio()); %> (V)', <% out.print(s.get(i).getPrecio()); %>, this.id);" type="checkbox" id="servicioVUELTAAD<% out.print(idx + "-" + i); %>" name="servicioVUELTAAD<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio() + "<br>");
                                            } else {
                                                if (rI.getPasajero(pasajero).isTutor()) {%>
                                                <input checked onchange="this.checked = true;" type="checkbox" id="servicioVUELTAAD<% out.print(idx + "-" + i); %>" name="servicioVUELTAAD<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>"><% out.print(s.get(i).getServicio() + " (Es tutor)<br>"); %>
                                                <%}
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <%  }%>
                        </div>
                        <%
                                pasajero++;
                            };
                            for (int idx = 0; idx < rI.getNninos(); idx++) {%>
                        <div class="botonMostrar" onclick="div('divNI<% out.print(idx); %>');">Mostrar/Ocultar Niño <% out.print(idx + 1); %></div>
                        <div class="pasaj" id="divNI<% out.print(idx); %>" style="display: none;">
                            <span><% out.print(rI.getPasajero(pasajero).getTratamiento()); %></span>
                            <span><% out.print(rI.getPasajero(pasajero).getNombre()); %></span>
                            <span><% out.print(rI.getPasajero(pasajero).getApellidos()); %></span>
                            <span>(<% out.print(rI.getPasajero(pasajero).getTipoIden().toUpperCase()); %>: </span>
                            <span><% out.print(rI.getPasajero(pasajero).getIdentificador()); %>) escoja sus servicios:</span><br><br>
                            <div id="serviciosIDA">
                                <h3>Servicios IDA:</h3>
                                <% for (int i = 0; i < s.size(); i++) {
                                        if (s.get(i).getServicio().equals("Reservar asiento")) {%>
                                <input onchange="cambioPrecios('<% out.print(s.get(i).getServicio()); %>', <% out.print(s.get(i).getPrecio()); %>, this.id);" onclick="if (this.checked) {
                                            document.getElementById('eje').click();
                                            ponerAsiento('AsientoNI<% out.print(idx + "" + i); %>');
                                        } else {
                                            AsientoNI<% out.print(idx + "" + i); %>.value = '';
                                            AsientoNI<% out.print(idx + "" + i); %>.innerHTML = '';
                                        }
                                        ;" type="checkbox" id="servicioIDANI<% out.print(idx + "-" + i); %>" name="servicioIDANI<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio()); %>
                                <input type="text" id="AsientoNI<% out.print(idx + "" + i); %>" name="AsientoNI<% out.print(idx + "" + i); %>" size="1" readonly><br>
                                <%} else {
                                    if (!s.get(i).getServicio().equals("Bebe")) {%>
                                <input onchange="cambioPrecios('<% out.print(s.get(i).getServicio()); %>', <% out.print(s.get(i).getPrecio()); %>, this.id);" type="checkbox" id="servicioIDANI<% out.print(idx + "-" + i); %>" name="servicioIDANI<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio() + "<br>");
                                            }
                                        }
                                    }%>
                            </div>
                            <% if (rI.getVueloVuelta() != null) {%>
                            <div id="serviciosVUELTA">
                                <h3>Servicios VUELTA:</h3>
                                <% for (int i = 0; i < s.size(); i++) {
                                        if (s.get(i).getServicio().equals("Reservar asiento")) {%>
                                <input onchange="cambioPreciosV('<% out.print(s.get(i).getServicio()); %> (V)', <% out.print(s.get(i).getPrecio()); %>, this.id);" onclick="if (this.checked) {
                                            document.getElementById('ejeV').click();
                                            ponerAsientoV('VAsientoNI<% out.print(idx + "" + i); %>');
                                        } else {
                                            VAsientoNI<% out.print(idx + "" + i); %>.value = '';
                                            VAsientoNI<% out.print(idx + "" + i); %>.innerHTML = '';
                                        }
                                        ;" type="checkbox" id="servicioVUELTANI<% out.print(idx + "-" + i); %>" name="servicioVUELTANI<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio()); %>
                                <input type="text" id="VAsientoNI<% out.print(idx + "" + i); %>" name="VAsientoNI<% out.print(idx + "" + i); %>" size="1" readonly><br>
                                <%} else {
                                    if (!s.get(i).getServicio().equals("Bebe")) {%>
                                <input onchange="cambioPreciosV('<% out.print(s.get(i).getServicio()); %> (V)', <% out.print(s.get(i).getPrecio()); %>, this.id);" type="checkbox" id="servicioVUELTANI<% out.print(idx + "-" + i); %>" name="servicioVUELTANI<% out.print(idx + "-" + i); %>" value="<% out.print(i); %>">
                                <%              out.print(s.get(i).getServicio() + "<br>");
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <%  }%>
                        </div>
                        <%
                                pasajero++;
                            };
                        %>
                        <div id="bajo">
                            <input type="submit" name="boton" id="continuar" value="Continuar" />
                        </div>
                    </div>
                </div>
                <div id="precios">
                    <div id="contenedorP">
                        <h3>A pagar:</h3>
                        <% double tot = 0; %>
                        <h5>IDA:
                            <% out.print(NombreIda + " &rArr; " + NombreVuelta); %> <br> Fecha: 
                            <% String fIDA = "" + rI.getVueloIda().getFecha().getDayOfMonth() + " - " + rI.getVueloIda().getFecha().getMonthValue() + " - " + rI.getVueloIda().getFecha().getYear();
                                String hIDA = "" + rI.getVueloIda().getHora_Salida();
                                out.print(fIDA + " ( " + hIDA + " )"); %></h5>
                        <p>Adultos: <%  out.print(rI.getNadultos()); %> ..... <% tot += rI.getNadultos() * rI.getVueloIda().getPrecio();
                            out.print(rI.getNadultos() * rI.getVueloIda().getPrecio() + " &euro;"); %>
                            <% if (rI.getNninos() != 0) { %> </p>
                        <p>Niños: <% out.print(rI.getNninos()); %> ..... <% tot += rI.getNninos() * rI.getVueloIda().getPrecio();
                            out.print(rI.getNninos() * rI.getVueloIda().getPrecio() + " &euro;"); %>
                            <%}%>
                        <div id="padreIDAN"></div>
                        <% if (rI.getNbebes() != 0) {%></p>
                        <p>Bebes: <% out.print(rI.getNbebes()); %> ..... <% tot += rI.getNbebes() * s.get(0).getPrecio();
                            out.print(rI.getNbebes() * s.get(0).getPrecio() + " &euro;"); %>
                            <%}%></p>
                        <h5>Servicios IDA</h5>
                        <div id="padreIDA"></div>
                        <% if (rI.getVueloVuelta() != null) { %>
                        <h5>VUELTA:
                            <% out.print(NombreVuelta + " &rArr; " + NombreIda); %> <br> Fecha: 
                            <% String fV = "" + rI.getVueloVuelta().getFecha().getDayOfMonth() + " - " + rI.getVueloVuelta().getFecha().getMonthValue() + " - " + rI.getVueloVuelta().getFecha().getYear();
                                String hV = "" + rI.getVueloVuelta().getHora_Salida();
                                out.print(fV + " ( " + hV + " )"); %></h5>
                        <p>Adultos: <%  out.print(rI.getNadultos()); %> ..... <% tot += rI.getNadultos() * rI.getVueloVuelta().getPrecio();
                            out.print(rI.getNadultos() * rI.getVueloVuelta().getPrecio() + " &euro;"); %>
                            <% if (rI.getNninos() != 0) { %> </p>
                        <p>Niños: <% out.print(rI.getNninos()); %> ..... <% tot += rI.getNninos() * rI.getVueloVuelta().getPrecio();
                            out.print(rI.getNninos() * rI.getVueloVuelta().getPrecio() + " &euro;"); %>
                            <%}
                                if (rI.getNbebes() != 0) {%></p>
                        <p>Bebes: <% out.print(rI.getNbebes()); %> ..... <% tot += rI.getNbebes() * s.get(0).getPrecio();
                            out.print(rI.getNbebes() * s.get(0).getPrecio() + " &euro;"); %>
                            <%}%></p>
                        <h5>Servicios VUELTA</h5>
                        <div id="padreVUELTA"></div>
                        <%
                            }
                        %>
                        <p><strong>TOTAL:</strong> <input readonly type="text" id="totalPagar" size="2" name="totalPagar" value="<% out.print(tot);%>">&euro;</p>
                    </div>
                </div>
            </div>
        </form>
        <a id="eje" href="#popup" ></a>
        <div class="modal-wrapper" id="popup">
            <div class="popup-contenedor">
                <h1>Escoja un asiento <span id="asientoPasaj"></span></h1>
                <div id="superior">
                    <%  Avion avIDA = (Avion) session.getAttribute("avIDA");
                        for (int idx = 0; idx < avIDA.getFilas(); idx++) {
                            if (idx == 0) {
                    %>
                    <div id="primera">
                        <%
                            for (int j = 0; j < avIDA.getColumnas(); j++) {

                        %>
                        <div class="asiento" onclick="cambio('asiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>', <% out.print("'" + (idx + 1) + "-" + (char) (65 + j) + "'"); %>);">
                            <img id="asiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>" src="../Imagenes/booked_seat_img.gif" alt="ocupada">
                            <p><% out.print("" + (idx + 1) + "-" + (char) (65 + j) + ""); %></p>
                        </div>
                        <%
                                if (j == 1) {
                                    out.print("<BR><BR>");
                                }
                            }
                        %> </div><%
                            }
                            if (idx == 1) {
                        %>
                    <div id="segunda">
                        <%
                            for (int j = 0; j < avIDA.getColumnas(); j++) {
                        %>
                        <div class="asiento2" onclick="cambio('asiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>', <% out.print("'" + (idx + 1) + "-" + (char) (65 + j) + "'"); %>);">
                            <img id="asiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>" src="../Imagenes/booked_seat_img.gif" alt="ocupada">
                            <p><% out.print("" + (idx + 1) + "-" + (char) (65 + j) + ""); %></p>
                        </div>
                        <%if (j == 1) {
                                    out.print("<BR><BR>");
                                }
                            }
                        %> </div><%
                                }
                            }
                        %>
                </div>
                Asiento escogido: <span id="asientoFinal"></span>
                <div id="bajo">
                    <input type="button" onclick="validarAsientoIDA();" name="boton" id="continuar" value="Continuar" /><br>
                    <div id="salidaMSG"></div>
                </div>
                <a id="aref" style="display: none;" class="popup-cerrar" href="#">X</a>
            </div>
        </div>

        <a id="ejeV" href="#popupV" ></a>
        <div class="modal-wrapper" id="popupV">
            <div class="popup-contenedor">
                <h1>Escoja un asiento <span id="VasientoPasaj"></span></h1>
                <div id="superiorV">
                    <%  if (session.getAttribute("avVuelta") != null) {

                            Avion avV = (Avion) session.getAttribute("avVuelta");
                            for (int idx = 0; idx < avV.getFilas(); idx++) {
                                if (idx == 0) {
                    %>
                    <div id="primeraV">
                        <%
                            for (int j = 0; j < avV.getColumnas(); j++) {

                        %>
                        <div class="asiento" onclick="cambioV('Vasiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>', <% out.print("'" + (idx + 1) + "-" + (char) (65 + j) + "'"); %>);">
                            <img id="Vasiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>" src="../Imagenes/booked_seat_img.gif" alt="ocupada">
                            <p><% out.print("" + (idx + 1) + "-" + (char) (65 + j) + ""); %></p>
                        </div>
                        <%
                                if (j == 1) {
                                    out.print("<BR><BR>");
                                }
                            }
                        %> </div><%
                            }
                            if (idx == 1) {
                        %>
                    <div id="segundaV">
                        <%
                            for (int j = 0; j < avIDA.getColumnas(); j++) {
                        %>
                        <div class="asiento2" onclick="cambioV('Vasiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>', <% out.print("'" + (idx + 1) + "-" + (char) (65 + j) + "'"); %>);">
                            <img id="Vasiento<% out.print((idx + 1) + "-" + (char) (65 + j)); %>" src="../Imagenes/booked_seat_img.gif" alt="ocupada">
                            <p><% out.print("" + (idx + 1) + "-" + (char) (65 + j) + ""); %></p>
                        </div>
                        <%if (j == 1) {
                                    out.print("<BR><BR>");
                                }
                            }
                        %> </div><%
                                    }
                                }
                            }
                        %>
                </div>
                Asiento escogido: <span id="VasientoFinal"></span>
                <div id="bajo">
                    <input type="button" onclick="validarAsientoV();" name="boton" id="continuar" value="Continuar" /><br>
                    <div id="salidaMSGV"></div>
                </div>
                <a id="arefV" style="display: none;" class="popup-cerrar" href="#">X</a>
            </div>
        </div>
    </body>
</html>
