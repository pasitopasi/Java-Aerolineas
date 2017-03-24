<%-- 
    Document   : despegue
    Created on : 06-mar-2017, 17:00:39
    Author     : pasito
--%>


<%@page import="Modelo.Vuelo"%>
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
        <title>Aterrizar</title>
    </head>
    <body>
        <div id="menu">
            <div id="imgLogo"><a href="../index.jsp"><img id="portada" src="../Imagenes/logo.png" alt="" ></a></div>
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
        <div class="usuarioR" id="F1">
            <form method="post" action="../DespegarVuelo">
                <h3>Aterrizar vuelo</h3>
                <% Vuelo v = (Vuelo) session.getAttribute("vuelo"); %>
                <table class="tablaViajes">
                    <tr>
                        <th>Código de vuelo</th>
                        <th>Fecha</th>
                        <th>Hora de salida</th>
                        <th>Hora de entrada</th>
                    </tr>
                    <tr>
                        <td><% out.print(v.getCODVuelo()); %></td>
                        <td><% out.print(v.getFecha().toString().substring(8, 10) + " - " + v.getFecha().toString().substring(5, 7) + " - " + v.getFecha().toString().substring(0, 4)); %></td>
                        <td><% out.print(v.getHora_Salida()); %></td>
                        <td><% out.print(v.getHora_Entrada());%></td>
                    </tr>
                </table>
                <input id="despegarB" type="submit" value="DESPEGAR"/>
            </form>
        </div>
    </body>
</html>
