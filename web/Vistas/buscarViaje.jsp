<%-- 
    Document   : buscarViaje
    Created on : 07-ene-2017, 9:23:31
    Author     : pasito
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Aeropuerto"%>
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
    <body onload="ponerFecha();viajes();">
        <div id="menu">
            <!-- <img id="portada" src="../Imagenes/logo.png" alt="" onclick="iAnimacion();">  -->
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
        <h1 id="titulo">1. Busqueda del viaje</h1>
        <div id="titulo01"></div>
        <div id="seleccion">
            <form method="post" action="../Dispatcher" onsubmit="return validar();">
                <div id="origen">
                    <p>Origen:</p>
                    <select  id="selecOrigen" name="selecOrigen"  onchange="viajesDestino(this.value);">
                        <option value="0">- Escoja un origen -</option>
                    </select>
                    <h6></h6>
                    <span>
                        <input type="radio" name="tipoVuelo" value="IDA" onclick="Ida()" checked/>IDA
                        <br>
                        <input type="date" name="fechaIDAVUELT" id="fechaIDAVUELT" required onblur="cambioFecha(this.value);">
                    </span>
                </div>
                <div id="destino">
                    <p>Destino:</p>
                    <select id="selecDestino" name="selecDestino"> 
                        <option value="">- Escoja un destino -</option>
                    </select>
                    <h6></h6>
                    <span>
                        <input type="radio" name="tipoVuelo" value="IDA + VUELTA" onclick="vuelta()">IDA + VUELTA
                        <br>
                        <input type="date" name="fechaIDAVUELTA" id="fechaIDAVUELTA" style="visibility:hidden;">
                    </span>
                </div>
                <div id="bajo">
                    <div id="pasaj1">
                        <p>Adulto:</p>
                        <input size="1" type="number" id="Nadulto" name="Nadulto" min="1" max="4" value="1" required>
                    </div>
                    <div id="pasaj2">
                        <p>Niño:</p>
                        <input size="1" type="number" id="Nnino" name="Nnino" min="0" max="4" value="0">
                    </div>
                    <div id="pasaj3">
                        <p>Bebe:</p>
                        <input size="1" type="number" id="Nbebe" name="Nbebe" min="0" max="4" value="0">
                    </div>
                    <br>
                    <br>
                    <input type="submit" name="boton" id="continuar" value="Buscar" />
                </div>
            </form>
            <div id="padre"></div>
        </div>
    </body>
</html>
