<%-- 
    Document   : finalizarReserva
    Created on : 08-feb-2017, 13:30:32
    Author     : pasito
--%>

<%@page import="Modelo.Servicio"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- No deja volver a esta pagina dandole al boton de atras del navegador -->
        <meta http-equiv="Expires" content="0"> 
        <meta http-equiv="Pragma" content="no-cache">
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
    <body>
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
        <h1 id="titulo">6. Finalizar reserva</h1>
        <div id="titulo06"></div>
        <div id="contenedorR">
            <%  Reserva rI = (Reserva) session.getAttribute("reservaI");
                String NombreIda = (String) session.getAttribute("NombreIda");
                String NombreVuelta = (String) session.getAttribute("NombreVuelta");%>
            <div id="Rida">
                <h3>VUELO DE IDA <br>( <% out.print(NombreIda); %> - <% out.print(NombreVuelta); %> )</h3>
                <p>Con código de vuelo: <% out.print(rI.getVueloIda().getCODVuelo()); %>.</p>
                <p>Se realizará el : <% out.print(rI.getVueloIda().getFecha().toString().substring(8, 10) + " - " + rI.getVueloIda().getFecha().toString().substring(5, 7) + " - " + rI.getVueloIda().getFecha().toString().substring(0, 4));%>
                    a las <% out.print(rI.getVueloIda().getHora_Salida()); %> </p>
                <p>Coste por pasajero <% out.print(rI.getVueloIda().getPrecio()); %> &euro;</p>
            </div>
            <% if (rI.getVueloVuelta() != null) {%>
            <div id="Rvuelta">
                <h3>VUELO DE VUELTA <br>( <% out.print(NombreIda); %> - <% out.print(NombreVuelta); %> )</h3>
                <p>Con código de vuelo: <% out.print(rI.getVueloVuelta().getCODVuelo()); %>.</p>
                <p>Se realizará el : <% out.print(rI.getVueloVuelta().getFecha().toString().substring(8, 10) + " - " + rI.getVueloVuelta().getFecha().toString().substring(5, 7) + " - " + rI.getVueloVuelta().getFecha().toString().substring(0, 4));%>
                    a las <% out.print(rI.getVueloVuelta().getHora_Salida()); %> </p>
                <p>Coste por pasajero <% out.print(rI.getVueloVuelta().getPrecio()); %> &euro;</p>
            </div>
            <%}%>
            <div id="contenidoT">
                <p>Total de pasajeros: <% out.print(rI.getNadultos() + rI.getNninos() + rI.getNbebes()); %></p>
                <%  int pasaj = 0;
                    for (int idx = 0; idx < rI.getNadultos(); idx++) { %>
                <p>Pasajero: <% out.print(rI.getPasajero(pasaj).getTratamiento()+ " " + rI.getPasajero(pasaj).getNombre() + " " + rI.getPasajero(pasaj).getApellidos() + " con " + rI.getPasajero(pasaj).getTipoIden() + " : " + rI.getPasajero(pasaj).getIdentificador()); %></p>
                <p>Servicio de IDA</p>
                <ol>
                    <%
                        Iterator<Servicio> itS = rI.getPasajero(pasaj).getServiciosIDA().iterator();
                        while (itS.hasNext()) {
                            Servicio se = itS.next();
                            if (se.getServicio().equals("Reservar asiento")) {
                                String fila = "" + rI.getPasajero(pasaj).getAsientoI().charAt(0);
                                String columna = "" + rI.getPasajero(pasaj).getAsientoI().charAt(2);
                                String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                            } else {
                                String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                        }
                    }%>
                </ol> <%
                    if (rI.getVueloVuelta() != null) {%>
                <p>Servicio de VUELTA</p>
                <ol>
                    <%
                        Iterator<Servicio> itV = rI.getPasajero(pasaj).getServiciosVUELTA().iterator();
                        while (itV.hasNext()) {
                            Servicio se = itV.next();
                            if (se.getServicio().equals("Reservar asiento")) {
                                String fila = "" + rI.getPasajero(pasaj).getAsientoV().charAt(0);
                                String columna = "" + rI.getPasajero(pasaj).getAsientoV().charAt(2);
                                String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                            } else {
                                String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                            }
                        }
                    %>
                </ol>
                <%
                        }
                        pasaj++;
                    }
                    for (int idx = 0; idx < rI.getNninos(); idx++) {
                        int posTutor = Integer.parseInt(rI.getPasajero(pasaj).getID_tutor());
                        String pasajero = rI.getPasajero(pasaj).getTratamiento()+ " " + rI.getPasajero(pasaj).getNombre() + " " + rI.getPasajero(pasaj).getApellidos()
                                + " con " + rI.getPasajero(pasaj).getTipoIden() + " : " + rI.getPasajero(pasaj).getIdentificador()
                                + " tiene como tutor en el viaje a " + rI.getPasajero(posTutor).getNombre() + " " + rI.getPasajero(posTutor).getApellidos();
                        %>
                <p>Pasajero: <% out.print(pasajero); %></p>
                <p>Servicio de IDA</p>
                <ol>
                    <%
                        Iterator<Servicio> itS = rI.getPasajero(pasaj).getServiciosIDA().iterator();
                        while (itS.hasNext()) {
                            Servicio se = itS.next();
                            if (se.getServicio().equals("Reservar asiento")) {
                                String fila = "" + rI.getPasajero(pasaj).getAsientoI().charAt(0);
                                String columna = "" + rI.getPasajero(pasaj).getAsientoI().charAt(2);
                                String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                            } else {
                                String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                        }
                    }%>
                </ol> <%
                    if (rI.getVueloVuelta() != null) {%>
                <p>Servicio de VUELTA</p>
                <ol>
                    <%
                        Iterator<Servicio> itV = rI.getPasajero(pasaj).getServiciosVUELTA().iterator();
                        while (itV.hasNext()) {
                            Servicio se = itV.next();
                            if (se.getServicio().equals("Reservar asiento")) {
                                String fila = "" + rI.getPasajero(pasaj).getAsientoV().charAt(0);
                                String columna = "" + rI.getPasajero(pasaj).getAsientoV().charAt(2);
                                String servi = se.getServicio() + ", asiento: " + fila + " - " + columna + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                            } else {
                                String servi = se.getServicio() + " con un precio de " + se.getPrecio() + " &euro;";
                                out.print("<li>" + servi + "</li>");
                            }
                        }
                    %>
                </ol>
                <%
                        }
                        pasaj++;
                    }
                    for (int idx = 0; idx < rI.getNbebes(); idx++) {
                        int posTutor = Integer.parseInt(rI.getPasajero(pasaj).getID_tutor());
                        String pasajero = rI.getPasajero(pasaj).getNombre() + " " + rI.getPasajero(pasaj).getApellidos()
                                + " con " + rI.getPasajero(pasaj).getTipoIden() + " : " + rI.getPasajero(pasaj).getIdentificador()
                                + " tiene como tutor en el viaje a " + rI.getPasajero(posTutor).getNombre() + " " + rI.getPasajero(posTutor).getApellidos();
                        %>
                        <p>Pasajero: <% out.print(pasajero); %></p>
                    <%
                        pasaj++;
                    }
                %>
                
                <p>El coste total es de <% out.print(rI.getaPagar()); %> &euro;</p>
                <div id="bajo">
                    <a href="../ReservarAccion"><button>Reservar</button></a>
                </div>
                <div id="padre"></div>
            </div>
        </div>
    </body>
</html>

