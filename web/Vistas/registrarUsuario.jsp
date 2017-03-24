<%-- 
    Document   : registrarUsuario
    Created on : 22-ene-2017, 10:57:39
    Author     : pasito
--%>

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
        <title>Acceder / Alta usuario</title>
    </head>
    <body>
        <div id="menu">
            <a href="../index.jsp"><img id="portada" src="../Imagenes/logo.png" alt="" ></a>
            <ul>
                <li><p><a href="buscarViaje.jsp">RESERVA</a></p></li>
                <li class="listaM"><p><a href="Facturar.jsp">FACTURAR</a></p></li>
                <li class="listaM"><p><a href="aterrizar.jsp">ATERRIZAR</a></p></li>
<li class="listaM"><p><a href="misReservas.jsp">ESTADISTICAS</a></p></li>
            </ul>
        </div>
        <div>
            <% if (session.getAttribute("errorSalida") != null) {%>
            <div class="usuarioR">
                <h1> <% out.print((String) session.getAttribute("errorSalida")); %></h1>
            </div>
            <%}%>
            <div class="usuarioR" id="bajoR">
                <h3>Acceder</h3>
                <form method="post" action="../accesoRegistro">
                    <label>Correo / Usuario:<input type="text" id="correo" name="correo" required></label>
                    <label>Contraseña:<input type="password" id="pass" name="pass" required></label>
                    <label>
                        <p><input name="control" type="submit" value="Acceder">
                            <span id="registrarP" onclick="mostrarRegistro();">Registrarte</span>
                        </p>
                    </label>
                </form>
            </div>
            <form name="form1" method="post" action="../montarCliente" onsubmit="return validarRegistro();">
                <div class="usuarioR" id="RegistrarFormulario">
                    <h3>Registrar</h3>
                    Cliente <br>
                    <select id="tratamiento" name="tratamiento">
                        <option value="Sr.">Sr.</option>
                        <option value="Sra.">Sra.</option>
                    </select>
                    <input type="text" id="Nombre" name="Nombre" placeholder="Nombre" required>  
                    <input type="text" id="Apell" name="Apell" placeholder="Apellidos" required>
                    <select id="tipoIDEN" name="tipoIDEN">
                        <option value="dni">DNI</option>
                        <option value="pasaporte">PASAPORTE</option>
                    </select>
                    <input type="text" id="dniU" name="dniU" placeholder="Número" required><br><br>
                    Dirección <br>
                    <select id="tipoDir" name="tipoDir" required>
                        <option value="">- Elija tipo-</option>
                        <option value="Calle">Calle</option>
                        <option value="Avenida">Avenida</option>
                        <option value="Bulevar">Bulevar</option>
                    </select>
                    <input type="text" name="nombreCalle" id="nombreCalle" required placeholder="Nombre">
                    <input type="number" name="numeroCalle" id="numeroCalle" required placeholder="Número">
                    <input type="text" name="letraCalle" id="letraCalle" placeholder="Piso, Letra"><br><br>
                    <input type="number" name="cp" id="cp" required placeholder="Código postal">
                    <input type="text" name="localidad" id="localidad" required placeholder="Localidad">
                    <input type="text" name="provincia" id="provincia" required placeholder="Provincia">
                    <input type="text" name="pais" id="pais" required placeholder="Pais"><br><br>

                    Correo electrónico: <input type="text" name="correoU" id="correoU" required placeholder="Correo electrónico">
                    Teléfono: 
                    <select required onchange="numTelefono(this.value);">
                        <option value="">- Elija pais-</option>
                        <option value="Es">España</option>
                    </select><input type="text" required id="telf" name="telf" placeholder="Teléfono"><br><br>
                    Usuario: <input type="text" name="usuario" id="usuario" required placeholder="Usuario"><br><br>
                    Contraseña:<input type="password" name="pass00" id="pass00" required placeholder="Contraseña">
                    Repita la contraseña:<input type="password" name="pass01" id="pass01" required placeholder="Contraseña">
                    <div id="bajo">
                        <input type="submit" name="boton" id="Registrar" value="Registrar" />
                    </div>
                    <div id="padre"></div>
                </div>
            </form>
        </div>

    </body>
</html>
