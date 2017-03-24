<%-- 
    Document   : vFacturar
    Created on : 15-feb-2017, 16:54:18
    Author     : pasito
--%>

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
        <title>Facturar</title>
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
        <% Reserva r = (Reserva) session.getAttribute("reservaF"); %>
        <div class="usuarioR" id="F1">
            <h3>FACTURAR</h3>
            <div id="vF1">
                <h3>IDA</h3>
                <%  if (r.getFacturacionIda() != null) {%>
                    <p>IDA FACTURADA<p>
                    <button disabled>FACTURAR</button>
                    <%} else {%>
                    <p>IDA NO FACTURADA<p>
                    <a href="../facturarIDA"><button>FACTURAR</button></a>
                <%}%>
            </div>
            <% if (r.getVueloVuelta() != null) {%>
            <div id="vF2">
                <h3>VUELTA</h3>
                <%  if (r.getFacturacionVuelta() != null) {%>
                    <p>VUELTA FACTURADA<p>
                    <button disabled>FACTURAR</button>
                    <%} else {%>
                    <p>VUELTA NO FACTURADA<p>
                    <a href="../facturarVUELTA"><button>FACTURAR</button></a>
                <%}%>
            </div>
            <%}%>
        </div>
    </body>
</html>
