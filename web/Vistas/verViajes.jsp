<%-- 
    Document   : verViajes
    Created on : 08-ene-2017, 20:02:54
    Author     : pasito
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Vuelo"%>
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
        <h1 id="titulo">2. Elección del viaje</h1>
        <div id="titulo02"></div>
        <%  if (session.getAttribute("vuelosIda") != null) {
                ArrayList<Vuelo> r = (ArrayList<Vuelo>) session.getAttribute("vuelosIda");
                String NombreIda = (String) session.getAttribute("NombreIda");
                String NombreVuelta = (String) session.getAttribute("NombreVuelta");
                if (r.size() != 0) {
        %>
        <div class="mostrarViajes">
            <h1>VIAJES IDA</h1>
            <h3> <% out.print(NombreIda + " &rArr; " + NombreVuelta); %> </h3>
            <form method="post" action="../Dispatcher" onsubmit="return validarViajes();">
                <table class="tablaViajes">
                    <tr>
                        <th>Código de vuelo</th>
                        <th>Fecha</th>
                        <th>Hora de salida</th>
                        <th>Hora de entrada</th>
                        <th>Precio</th>
                        <th>Plazas libres</th>
                        <th>Seleccionar</th>
                    </tr>
                    <%
                        Iterator<Vuelo> it = r.iterator();
                        while (it.hasNext()) {
                            Vuelo v = it.next();
                    %>
                    <tr>
                        <td><% out.print(v.getCODVuelo()); %></td>
                        <td><% out.print(v.getFecha().toString().substring(8, 10) + " - " + v.getFecha().toString().substring(5, 7) + " - " + v.getFecha().toString().substring(0, 4)); %></td>
                        <td><% out.print(v.getHora_Salida()); %></td>
                        <td><% out.print(v.getHora_Entrada()); %></td>
                        <td><% out.print(v.getPrecio()); %>&euro;</td>
                        <td><% out.print(v.getPlazas()); %></td>
                        <td>
                            <%if (v.getPlazas() != 0) {%>
                            <input type="radio" id="IDA" name="IDA" value="<% out.print(v.getID()); %>"/>
                            <%} else {%>
                            LLENO
                            <%}%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <%          if (session.getAttribute("vuelosVuelta") != null) {
                        ArrayList<Vuelo> rv = (ArrayList<Vuelo>) session.getAttribute("vuelosVuelta");
                        if (rv.size() != 0) {
                %>
                <h6></h6>
                <h1>VIAJES VUELTA</h1>
                <h3> <% out.print(NombreVuelta + " &rArr; " + NombreIda); %> </h3>
                <table class="tablaViajes">
                    <tr>
                        <th>Código de vuelo</th>
                        <th>Fecha</th>
                        <th>Hora de salida</th>
                        <th>Hora de entrada</th>
                        <th>Precio</th>
                        <th>Plazas libres</th>
                        <th>Seleccionar</th>
                    </tr>
                    <%
                        Iterator<Vuelo> it1 = rv.iterator();
                        while (it1.hasNext()) {
                            Vuelo v = it1.next();
                    %>
                    <tr>
                        <td><% out.print(v.getCODVuelo()); %></td>
                        <td><% out.print(v.getFecha().toString().substring(8, 10) + " - " + v.getFecha().toString().substring(5, 7) + " - " + v.getFecha().toString().substring(0, 4)); %></td>
                        <td><% out.print(v.getHora_Salida()); %></td>
                        <td><% out.print(v.getHora_Entrada()); %></td>
                        <td><% out.print(v.getPrecio()); %>&euro;</td>
                        <td><% out.print(v.getPlazas()); %></td>
                        <td>
                            <input type="radio" id="VUELTA" name="VUELTA" value="<% out.print(v.getID()); %>"/>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <%}%>

                <%
                    }%>
                <div id="padre"><p></p></div>
                <input type="submit" name="boton" value="Continuar">
            </form>
        </div>
        <%

        } else {
        %>
    </div>
    <div class="mostrarViajes"> 
        <h1>No hay viajes</h1>
    </div> 
    <%}
        }
    %>

</body>
</html>