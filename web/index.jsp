<%-- 
    Document   : index
    Created on : 03-ene-2017, 11:48:04
    Author     : pasito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css" title="style" />
        <script type="text/javascript" src="JavaScript/java.js">
        </script>
        <title>Inicio</title>
    </head>
    <body>
        <div id="menu">
            <img id="portada" src="Imagenes/logo.png" alt="" onclick="indexAnimacion();">
            <ul>
                <li><p><a href="Vistas/buscarViaje.jsp">RESERVA</a></p></li>
                <li class="listaM"><p><a href="Vistas/Facturar.jsp">FACTURAR</a></p></li>
                <li class="listaM"><p><a href="Vistas/aterrizar.jsp">ATERRIZAR</a></p></li>
                <li class="listaM"><p><a href="Vistas/misReservas.jsp">ESTADISTICAS</a></p></li>
                <% if (session.getAttribute("usuario") == null) { %>
                <li id="acceso"><p><a href="" ><p id="prueba">Acceder</p></a></p>
                    <ul id="cajon">
                        <form method="post" action="javascript: ObtenerUserIndex();">
                            <li>Correo / Usuario:<input type="text" id="correo" name="correo" required></li>
                            <li>Contraseña:<input type="password" id="pass" name="pass" required></li>
                            <li>
                                <p><input name="control" type="submit" value="Acceder">
                                    <a href="DispatcherAcceso?control=Registrar">Registrar</a></p>
                            </li>
                        </form>
                    </ul>
                </li>         
                <%
                } else { %>
                <li id="acceso"><p><a href=""> <p id="prueba"><% out.print(session.getAttribute("usuario")); %></p></a></p>
                    <ul id="cajon">
                        <li>
                            <p><a href="./DispatcherAcceso?control=Desconectar">Desconectar</a></p>
                        </li>
                    </ul>
                </li>
                <% }%>
            </ul>
        </div>
        <img id="imagenTorre" src="Imagenes/control_tower1.png" alt="torre de control">
        <div class="usuarioR" id="F2">
            <h2>Politica de la empresa</h2>
            <div id="texto">
                <p>Normativa de la empresa:</p>
                <ol>
                    <li><p>Desconectar.</p></li>
                    <li><p>Disfrutar.</p></li>
                    <li><p>Y pulsar sobre AirPasito y desvelarás la mágia.</p></li>
                </ol>
            </div>
        </div>
        <div id="F3"></div>
    </body>
</html>
