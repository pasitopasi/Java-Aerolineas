<%-- 
    Document   : aterrizar
    Created on : 17-feb-2017, 12:08:35
    Author     : pasito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../CSS/estilo.css" title="style" />
        <script type="text/javascript" src="../JavaScript/java.js">
        </script>
        <title>Aterrizar</title>
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
        <div class="usuarioR" id="F1">
            <form method="post" action="../buscaVuelo">
                <h3>Aterrizar Vuelo</h3>
                <label>Código de vuelo: <input type="text" id="codvuelo" name="codvuelo" required></label>
                <label>Fecha: <input type="date" id="fechavuelo" name="fechavuelo" required></label>
                <label>
                    <p><input name="control" type="submit" value="Buscar"></p>
                </label>
            </form>
                <% if (session.getAttribute("sF") != null) { %>
            <h1><% out.print(session.getAttribute("sF"));
                   session.removeAttribute("sF");%></h1>
            <%}%>
        </div>
    </body>
</html>
