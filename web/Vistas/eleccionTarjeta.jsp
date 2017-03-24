<%-- 
    Document   : eleccionTarjeta
    Created on : 27-ene-2017, 10:34:07
    Author     : pasito
--%>

<%@page import="Modelo.Reserva"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Tarjeta"%>
<%@page import="java.util.ArrayList"%>
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
        <h1 id="titulo">5. Pago</h1>
        <div id="titulo05"></div>
        <div class="usuarioR">
            <h3>Total a pagar: <% Reserva rI = (Reserva) session.getAttribute("reservaI");
                            out.print(rI.getaPagar()); %>
            </h3>
            <form method="post" action="../MontarReserva" onsubmit="return validarReserva();-">
            <table id="tarjetasTabla">
                <tr>
                    <th>TIPO</th>
                    <th>NÚMERO</th>
                    <th>CADUCIDAD</th>
                    <th>Pagar</th>
                </tr>
                <%  ArrayList<Tarjeta> tarjetas = (ArrayList<Tarjeta>) session.getAttribute("tarjetas");
                    if (tarjetas.size() != 0) {
                        Iterator<Tarjeta> it = tarjetas.iterator();
                        while (it.hasNext()) {
                            Tarjeta t = it.next();
                %>
                <tr>
                    <% if (t.getTipo().equals("MasterCard")) {%> 
                    <td><img id="" src="../Imagenes/mastercard.png" alt="master" /></td>
                        <%}
                       if (t.getTipo().equals("Visa")) {%>
                    <td><img id="" src="../Imagenes/visa.png" alt="visa" /></td>
                        <% } 
                       if (t.getTipo().equals("American Express")) {%>
                    <td><img id="" src="../Imagenes/americanexpress.png" alt="visa" /></td>
                        <% } %>
                    <td>*<% out.print(t.getNumero()); %></td>
                    <td><% out.print(t.getMesCaducidad() + "/" + t.getAñoCaducidad()); %></td>
                    <td><input type="radio" id="tarjeta<% out.print(t.getID()); %>" name="tarjeta" value="<% out.print(t.getID()); %>"></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <div id="bajo">
                <input type="submit" name="boton" id="continuar" value="Pagar" /><br>
            </div>
            </form>
            <div id="addTarjeta" onclick="mostrarTarjetas();">+ Añadir tarjeta</div>
            <div id="crearTarjeta">
                <form method="post" onsubmit=" return validarTarjeta();" action="javascript: addTarjeta();" >
                    <span id="tipoT" name="tipoT"></span>
                    <div>
                        Número: 
                        <input required type="text" maxlength="19" id="numeroTarjeta" onkeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;eleccionTarjeta();" name="numeroTarjeta" placeholder="Número" >
                        <input required size="2" type="number" min="0" max="999" maxlength="3" id="csvTarjeta" name="csvTarjeta" placeholder="CSV" >
                        <img id="imgTarjeta" src="" alt="" />
                    </div>
                    <br><br>
                    Fecha caducidad de la tarjeta: 
                    <input required size="2" type="number" min="1" max="12" maxlength="2" id="mesTarjeta" name="mesTarjeta" placeholder="Mes" >
                    <input required size="2" type="number" min="2017" max="2025" maxlength="2" id="anoTarjeta" name="anoTarjeta" placeholder="Año" >
                    <input type="submit" name="boton" id="continuar" value="Añadir tarjeta" ><br>
                </form>
            </div>
        </div>
    </body>
</html>
