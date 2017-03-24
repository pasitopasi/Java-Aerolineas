<%-- 
    Document   : crearPasajeros
    Created on : 12-ene-2017, 9:08:11
    Author     : pasito
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Servicio"%>
<%@page import="Modelo.Reserva"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%  // Aqui vamos a crear las fechas correspondientes a cada tipo de 
    // pasajero, realizare el calculo ahora y solo tendre que llamar
    // a las variables correspondientes.

    java.util.Calendar fecha = java.util.Calendar.getInstance();
    int yearActual = fecha.get(java.util.Calendar.YEAR);
    int mesA = fecha.get(java.util.Calendar.MONTH) + 1;
    String mesActual = "" + mesA;
    if (mesA < 10) {
        mesActual = "0" + mesA;
    }
    
    int year = fecha.get(java.util.Calendar.YEAR) - 18;
    int minniño = fecha.get(java.util.Calendar.YEAR) - 2;
    int mes = fecha.get(java.util.Calendar.MONTH) + 1;
    int dia = fecha.get(java.util.Calendar.DATE);
    int dia1 = fecha.get(java.util.Calendar.DATE) - 1;
    String mesS = "" + mes;
    String diaS = "" + dia;
    String diaS1 = "" + dia1;
    if (mes < 10) {
        mesS = "0" + mes;
    }
    if (dia < 10) {
        diaS = "0" + dia;
    }
    if (dia1 < 10) {
        diaS1 = "0" + dia1;
    }
    //minimo caducidad
    String minNacio = yearActual + "-" + mesActual + "-" + diaS;
    //minimo adulto
    String minAdulto = year + "-" + mesS + "-" + diaS;
    //maximo niño
    String maxNino = year + "-" + mesS + "-" + diaS1;
    //minimo niño
    String minNino = minniño + "-" + mesS + "-" + diaS;
    //maximo bebe
    String maxBebe = minniño + "-" + mesS + "-" + diaS1;
    //minimo bebe
    String minBebe = fecha.get(java.util.Calendar.YEAR) + "-" + mesS + "-" + diaS;

    String NombreIda = (String) session.getAttribute("NombreIda");
    String NombreVuelta = (String) session.getAttribute("NombreVuelta");
    ArrayList<Servicio> s = (ArrayList<Servicio>) session.getAttribute("servicios");
%>
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
        <h1 id="titulo">3. Datos personales</h1>
        <div id="titulo03"></div>
        <div id="superServicios">
            <form method="post" action="../MontarPasajeros" onload="this.form.reset();">
                <div id="servicios">
                    <div id="pasajerosS">
                        <%  if (session.getAttribute("reservaI") == null) {
                                response.sendRedirect("../index.jsp");
                            }
                            Reserva rI = (Reserva) session.getAttribute("reservaI");
                            for (int idx = 0; idx < rI.getNadultos(); idx++) {
                        %>
                        <div class="botonMostrar" onclick="div('divAD<% out.print(idx); %>');">Mostrar/Ocultar Adulto <% out.print(idx + 1); %></div>
                        <div class="pasaj" id="divAD<% out.print(idx); %>" style="display: none;">
                            <select id="tratamientoAD<% out.print(idx); %>" name="tratamientoAD<% out.print(idx); %>">
                                <option value="Sr.">Sr.</option>
                                <option value="Sra.">Sra.</option>
                            </select>
                            <input type="text" id="NombreAD<% out.print(idx); %>" name="NombreAD<% out.print(idx); %>" placeholder="Nombre" required>  
                            <input type="text" id="ApellAD<% out.print(idx); %>" name="ApellAD<% out.print(idx); %>" placeholder="Apellidos" required><br><br>
                            <select id="eleccionAD<% out.print(idx); %>" name="eleccionAD<% out.print(idx); %>"> 
                                <option value="dni">DNI</option>
                                <option value="pasaporte">PASAPORTE</option>
                            </select>
                            <input type="text" id="DNIAD<% out.print(idx); %>" name="DNIAD<% out.print(idx); %>" required placeholder="Número" onchange="validarDNIPantalla('eleccionAD<% out.print(idx); %>', this.value, 'DNIAD<% out.print(idx); %>');" >
                            Fecha caducidad: <input type="date" name="fechaCadAD<% out.print(idx); %>" id="fechaCadAD<% out.print(idx); %>" required min="<% out.print(minNacio); %>"><br><br>
                            Fecha nacimiento: <input type="date" name="fechaNacAD<% out.print(idx); %>" id="fechaNacAD<% out.print(idx); %>" required max="<% out.print(minAdulto); %>">
                            Nacionalidad: 
                            <select onchange="validarnNacio(this.value, 'eleccionAD<% out.print(idx); %>', 'DNIAD<% out.print(idx); %>');" id="NacAD<% out.print(idx); %>" name="NacAD<% out.print(idx); %>" required> 
                                <option value="">- Escoge una -</option>
                                <option value="ES">Española</option>
                                <option value="OTRA">Otra</option>
                            </select>
                            <br>
                        </div>
                        <%
                            };
                            for (int idx = 0; idx < rI.getNninos(); idx++) {
                        %>
                        <div class="botonMostrar" onclick="div('divNI<% out.print(idx); %>');">Mostrar/Ocultar Niño <% out.print(idx + 1); %></div>
                        <div class="pasaj" id="divNI<% out.print(idx); %>" style="display: none;">
                            <select id="tratemientoNI<% out.print(idx); %>" name="tratemientoNI<% out.print(idx); %>">
                                <option value="Don">Don</option>
                                <option value="Doña">Doña</option>
                            </select>
                            <input type="text" id="NombreNI<% out.print(idx); %>" name="NombreNI<% out.print(idx); %>" placeholder="Nombre" required>  
                            <input type="text" id="ApellNI<% out.print(idx); %>" name="ApellNI<% out.print(idx); %>" placeholder="Apellidos" required><br><br>
                            <select id="eleccionNI<% out.print(idx); %>" name="eleccionNI<% out.print(idx); %>">
                                <option value="dni">DNI</option>
                                <option value="pasaporte">PASAPORTE</option>
                            </select>
                            <input type="text" id="DNINI<% out.print(idx); %>" name="DNINI<% out.print(idx); %>" placeholder="Número" required onchange="validarDNIPantalla('eleccionNI<% out.print(idx); %>', this.value, 'DNINI<% out.print(idx); %>');">
                            Fecha caducidad <input type="date" name="fechaCadNI<% out.print(idx); %>" id="fechaCadNI<% out.print(idx); %>" required min="<% out.print(minNacio); %>"><br><br>
                            Fecha nacimiento: <input type="date" name="fechaNacNI<% out.print(idx); %>" id="fechaNacNI<% out.print(idx); %>" max="<% out.print(minNino); %>" min="<% out.print(maxNino); %>" required>
                            Nacionalidad: 
                            <select onchange="validarnNacio(this.value, 'eleccionNI<% out.print(idx); %>', 'DNINI<% out.print(idx); %>');" id="NacNI<% out.print(idx); %>" name="NacNI<% out.print(idx); %>" required> 
                                <option value="">- Escoge una -</option>
                                <option value="ES">Española</option>
                                <option value="OTRA">Otra</option>
                            </select>
                            <br><br>
                            Tutor: <select id="tutorNI<% out.print(idx); %>" name="tutorNI<% out.print(idx); %>" required>
                                <option value="">- Escoja un adulto -</option>
                                <% for (int j = 0; j < rI.getNadultos(); j++) {%>
                                <option value="<% out.print(j); %>">Adulto <% out.print(j + 1); %></option>
                                <%}%>
                            </select>
                        </div>
                        <%
                            };
                            for (int idx = 0; idx < rI.getNbebes(); idx++) {
                        %>
                        <div class="botonMostrar" onclick="div('divBE<% out.print(idx); %>');">Mostrar/Ocultar Bebe <% out.print(idx + 1); %></div>
                        <div class="pasaj" id="divBE<% out.print(idx); %>" style="display: none;">
                            <input type="text" id="NombreBE<% out.print(idx); %>" name="NombreBE<% out.print(idx); %>" placeholder="Nombre" required>  
                            <input type="text" id="ApellBE<% out.print(idx); %>" name="ApellBE<% out.print(idx); %>" placeholder="Apellidos" required><br><br>
                            <select name="eleccionBE<% out.print(idx); %>" id="eleccionBE<% out.print(idx); %>">
                                <option value="dni">DNI</option>
                                <option value="pasaporte">PASAPORTE</option>
                            </select>
                            <input type="text" id="DNIBE<% out.print(idx); %>" name="DNIBE<% out.print(idx); %>" placeholder="Número" required onchange="validarDNIPantalla('eleccionBE<% out.print(idx); %>', this.value, 'DNIBE<% out.print(idx); %>');"> 
                            Fecha caducidad: <input type="date" name="fechaCadBE<% out.print(idx); %>" id="fechaCadBE<% out.print(idx); %>" required min="<% out.print(minNacio); %>"><br><br>
                            Fecha nacimiento: <input type="date" name="fechaNacBE<% out.print(idx); %>" id="fechaNacBE<% out.print(idx); %>" max="<% out.print(minBebe); %>" min="<% out.print(maxBebe); %>" required>
                            Nacionalidad: 
                            <select onchange="validarnNacio(this.value, 'eleccionBE<% out.print(idx); %>', 'DNIBE<% out.print(idx); %>');" id="NacBE<% out.print(idx); %>" name="NacBE<% out.print(idx); %>" required> 
                                <option value="">- Escoge una -</option>
                                <option value="ES">Española</option>
                                <option value="OTRA">Otra</option>
                            </select>
                            <br><br>
                            Tutor: <select id="tutorBE<% out.print(idx); %>" name="tutorBE<% out.print(idx); %>" required>
                                <option value="">- Escoja un adulto -</option>
                                <% for (int j = 0; j < rI.getNadultos(); j++) {%>
                                <option value="<% out.print(j); %>">Adulto <% out.print(j + 1); %></option>
                                <%}%>
                            </select>
                        </div>
                        <% }%>
                        <div id="bajo">
                            <input type="submit" name="boton" id="continuar" value="Proceder" />
                        </div>
                    </div>
            </form>
        </div>
    </div>
    <div id="precios">
        <div id="contenedorP">
            <h3>A pagar:</h3>
            <% double tot = 0; %>
            <h5>IDA:
                <% out.print(NombreIda + " &rArr; " + NombreVuelta); %> <br> Fecha: 
                <% String fIDA = ""+rI.getVueloIda().getFecha().getDayOfMonth()+" - "+rI.getVueloIda().getFecha().getMonthValue()+" - "+rI.getVueloIda().getFecha().getYear();
                       String hIDA = ""+ rI.getVueloIda().getHora_Salida();
                out.print(fIDA+" ( "+hIDA+" )"); %></h5>
            <p>Adultos: <%  out.print(rI.getNadultos()); %> ..... <% tot += rI.getNadultos() * rI.getVueloIda().getPrecio();
                out.print(rI.getNadultos() * rI.getVueloIda().getPrecio() + " &euro;"); %>
            <% if (rI.getNninos() != 0) { %> </p>
            <p>Niños: <% out.print(rI.getNninos()); %> ..... <% tot += rI.getNninos() * rI.getVueloIda().getPrecio();
                out.print(rI.getNninos() * rI.getVueloIda().getPrecio() + " &euro;"); %>
                <%}%>
            <% if (rI.getNbebes() != 0) {%></p>
            <p>Bebes: <% out.print(rI.getNbebes()); %> ..... <% tot += rI.getNbebes() * s.get(0).getPrecio();
                out.print(rI.getNbebes() * s.get(0).getPrecio() + " &euro;"); %>
                <%}%></p>

            <% if (rI.getVueloVuelta() != null) { %>
            <h5>VUELTA:
                <% out.print(NombreVuelta + " &rArr; " + NombreIda); %> <br> Fecha: 
                <% String fV = ""+rI.getVueloVuelta().getFecha().getDayOfMonth()+" - "+rI.getVueloVuelta().getFecha().getMonthValue()+" - "+rI.getVueloVuelta().getFecha().getYear();
                       String hV = ""+ rI.getVueloVuelta().getHora_Salida();
                out.print(fV+" ( "+hV+" )"); %></h5>
            <p>Adultos: <%  out.print(rI.getNadultos()); %> ..... <% tot += rI.getNadultos() * rI.getVueloVuelta().getPrecio();
                out.print(rI.getNadultos() * rI.getVueloVuelta().getPrecio() + " &euro;"); %>
                <% if (rI.getNninos() != 0) { %> </p>
            <p>Niños: <% out.print(rI.getNninos()); %> ..... <% tot += rI.getNninos() * rI.getVueloVuelta().getPrecio();
                out.print(rI.getNninos() * rI.getVueloVuelta().getPrecio() + " &euro;"); %>
                <%}
                    if (rI.getNbebes() != 0) {%></p>
            <p>Bebes: <% out.print(rI.getNbebes()); %> ..... <% tot += rI.getNbebes() * s.get(0).getPrecio();
                out.print(rI.getNbebes() * s.get(0).getPrecio() + " &euro;"); %>
                <%}%></p>
            <%
                }
            %>
            <p><strong>TOTAL:</strong> <input type="text" readonly id="totalPagar" size="2" name="totalPagar" value="<% out.print(tot);%>"> &euro;</p>
        </div>
    </div>
</body>
</html>
