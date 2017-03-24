<%-- 
    Document   : misReservas
    Created on : 17-feb-2017, 12:08:45
    Author     : pasito
--%>

<%@page import="Modelo.ServicioEstadistico"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Estadistica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../CSS/estilo.css" title="style" />
        <script type="text/javascript" src="../JavaScript/java.js">
        </script>
        <title>Estadisticas</title>
    </head>
    <body onload="viajes();">
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
                <li id="acceso"><p><a href=""> <p id="prueba"><% out.print(session.getAttribute("usuario")); %></p></a></p>
                    <ul id="cajon">
                        <li>
                            <p><a href="../DispatcherAcceso?control=Desconectar">Desconectar</a></p>
                        </li>
                    </ul>
                </li>
                <% }%>
            </ul>
        </div>
        <h1 id="titulo">Estadisticas</h1>
        <div id="seleccion1">
            <form method="post" action="../Estadisticas">
                <div id="origen">
                    <p>Origen:</p>
                    <select  id="selecOrigen" name="selecOrigen"  onchange="viajesDestino(this.value);" required>
                        <option value="">- Escoja un origen -</option>
                    </select>
                    <h6></h6>
                    <span>
                        <label>Fecha inicio</label>
                        <br>
                        <input type="date" name="fechaMIN" id="fechaMIN" required>
                    </span>
                </div>
                <div id="destino">
                    <p>Destino:</p>
                    <select id="selecDestino" name="selecDestino" required> 
                        <option value="">- Escoja un destino -</option>
                    </select>
                    <h6></h6>
                    <span>
                        <label>Fecha final</label>
                        <br>
                        <input type="date" name="fechaMAX" id="fechaMAX" required>
                    </span>
                </div>
                <input type="submit" name="boton" id="continuar1" value="Buscar" />
            </form>
            <div id="padre"></div>
        </div>
        <% if (session.getAttribute("msgSalida") != null) { %>
        <div class="usuarioR" id="F1">
            <h1><% out.print(session.getAttribute("msgSalida"));
                session.removeAttribute("msgSalida");%></h1>
        </div>
        <%}%>
        <% if (session.getAttribute("estadistica") != null) { %>
        <div class="usuarioR" id="F2">
            <% Estadistica estadisticaF = (Estadistica) session.getAttribute("estadistica");%>
            <h4>Entre <% out.print(estadisticaF.getFechaMIN().toString().substring(8, 10) + " - " + estadisticaF.getFechaMIN().toString().substring(5, 7) + " - " + estadisticaF.getFechaMIN().toString().substring(0, 4)); %> y <% out.print(estadisticaF.getFechaMAX().toString().substring(8, 10) + " - " + estadisticaF.getFechaMAX().toString().substring(5, 7) + " - " + estadisticaF.getFechaMAX().toString().substring(0, 4)); %>
            </h4>
            <h4> Entre <% out.print(estadisticaF.getNombreOrigen()); %> y <% out.print(estadisticaF.getNombreDestino());%> </h4>    

            <table class="tablaViajes">
                <tr>
                    <th>SERVICIO</th>
                    <th>IMPORTE ACUMULADO</th>
                </tr>
                <% Iterator<ServicioEstadistico> it = estadisticaF.getServicioEstadistico().iterator();
                    while (it.hasNext()) {
                        ServicioEstadistico s = it.next();%>
                <tr>
                    <td><% out.print(s.getServicio()); %></td>
                    <td><% out.print(s.getTotalCantidadPrecio()); %>€</td>
                </tr>
                <%}%>
            </table>
            <% session.removeAttribute("estadistica");%>
        </div>
        <%}%>

    </body>
</html>
