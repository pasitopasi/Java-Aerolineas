<%-- 
    Document   : salidaFacturar
    Created on : 16-feb-2017, 9:17:46
    Author     : pasito
--%>

<%@page import="Modelo.Pasajero"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Reserva"%>
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
        <div id="sf1">
            <% Vuelo v = (Vuelo) session.getAttribute("vuelo");
                Reserva r = (Reserva) session.getAttribute("reservaF");
            %>
            <h2>CÓDIGO DE VUELO: <% out.print(v.getCODVuelo()); %></h2>
            <p>Salida : <% out.print(v.getHora_Salida()); %> del 
                <% out.print(v.getFecha().toString().substring(8, 10) + " - " + v.getFecha().toString().substring(5, 7) + " - " + v.getFecha().toString().substring(0, 4)); %>
            </p>
            <h3>PASAJEROS:</h3>
            <% Iterator<Pasajero> it = r.getPasajeros().iterator();
                while (it.hasNext()) {
                    Pasajero p = it.next();
                    if (!p.getTratamiento().equals("")) {
                         if (p.getNacionalidad().equals("ES")) {%>
                            <p><% out.print(p.sf()); %> tiene nacionalidad española.</p> <p><span></span>Tiene el asiento <% out.print(p.getAsientoI()); %></p>
                         <%}else{%>
                            <p><% out.print(p.sf()); %> tiene otra nacionalidad diferente a la española.</p> <p><span></span>Tiene el asiento <% out.print(p.getAsientoI()); %></p>
                        <%}
                    } else {
                        if (p.getNacionalidad().equals("ES")) {%>
                            <p><% out.print(p.sf()); %> tiene nacionalidad española.</p>
                        <%}else{%>
                            <p><% out.print(p.sf()); %> tiene otra nacionalidad diferente a la española.</p>
                    <%}  }    
                }%>
            <a href="../ObtenerFacturacionPDF" target="_blank"><button>Obtener PDF</button></a>
        </div>
    </body>
</html>
