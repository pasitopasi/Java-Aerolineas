function indexAnimacion() {
    document.getElementById('portada').setAttribute('style', 'animation-name: ejemplo;');
    setTimeout(borrarAnimacion, 3000);
}
function borrarAnimacion() {
    document.getElementById('portada').removeAttribute('style');
}
function ponerFecha() {
    var d = new Date();
    var mes = d.getMonth() + 1;
    if ((d.getMonth() + 1) < 10)
        mes = "0" + (d.getMonth() + 1);
    var dia = d.getDate();
    if (d.getDate() < 10)
        dia = "0" + d.getDate();
    var fecha = d.getFullYear() + "-" + mes + "-" + dia;
    document.getElementById("fechaIDAVUELT").min = fecha;
    document.getElementById("fechaIDAVUELT").value = fecha;
}
function cambioFecha(fecha) {
    document.getElementById("fechaIDAVUELTA").min = fecha;
    document.getElementById("fechaIDAVUELTA").value = fecha;
}
function validar01() {
    borrarS();
    var indice = document.getElementById("selecOrigen").selectedIndex;
    if (indice == null || indice == 0 || indice == "") {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color: red; text-align: center;");
        titulo.appendChild(document.createTextNode("Seleccione un origen."));
        padre.appendChild(titulo);
        return false;
    }
    var indice1 = document.getElementById("selecDestino").selectedIndex;
    if (indice1 == null || indice1 == 0 || indice1 == "") {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color: red; text-align: center;");
        titulo.appendChild(document.createTextNode("Seleccione un destino."));
        padre.appendChild(titulo);
        return false;
    }
}
function validar() {
    borrarS();
    var indice = document.getElementById("selecOrigen").selectedIndex;
    if (indice == null || indice == 0 || indice == "") {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color: red; text-align: center;");
        titulo.appendChild(document.createTextNode("Seleccione un origen."));
        padre.appendChild(titulo);
        return false;
    }
    var indice1 = document.getElementById("selecDestino").selectedIndex;
    if (indice1 == null || indice1 == 0 || indice1 == "") {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color: red; text-align: center;");
        titulo.appendChild(document.createTextNode("Seleccione un destino."));
        padre.appendChild(titulo);
        return false;
    }
    var opciones = document.getElementsByName("tipoVuelo");
    var seleccionado = false;
    for (var i = 0; i < opciones.length; i++) {
        if (opciones[i].checked) {
            seleccionado = true;
            break;
        }
    }
    if (!seleccionado) {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color: red; text-align: center;");
        titulo.appendChild(document.createTextNode("Seleccione un tipo de vuelo."));
        padre.appendChild(titulo);
        return false;
    }
    return true;
}
function borrarS() {
    if (document.getElementById("p1")) {
        document.getElementById("p1").parentNode.removeChild(document.getElementById("p1"));
    }
}
function Ida() {
    document.getElementById("fechaIDAVUELTA").style.visibility = "hidden";
    document.getElementById("fechaIDAVUELTA").required = false;
}
function vuelta() {
    document.getElementById("fechaIDAVUELTA").style.visibility = "visible";
    var fecha = document.getElementById("fechaIDAVUELT").value;
    document.getElementById("fechaIDAVUELTA").min = fecha;
    document.getElementById("fechaIDAVUELTA").value = fecha;
    document.getElementById("fechaIDAVUELTA").required = true;
}
function validarViajes() {
    borrarViaje();
    var opciones = document.getElementsByName("IDA");
    var seleccionado = false;
    for (var i = 0; i < opciones.length; i++) {
        if (opciones[i].checked) {
            seleccionado = true;
            break;
        }
    }
    if (!seleccionado) {
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color:red;");
        titulo.appendChild(document.createTextNode("Seleccione una ida."));
        padre.appendChild(titulo);
        return false;
    }
    if (document.getElementsByName("VUELTA")[0].value != null) {
        var opciones = document.getElementsByName("VUELTA");
        var seleccionado = false;
        for (var i = 0; i < opciones.length; i++) {
            if (opciones[i].checked) {
                seleccionado = true;
                break;
            }
        }
        if (!seleccionado) {
            var padre = document.getElementById("padre");
            var titulo = document.createElement("p");
            titulo.setAttribute("id", "p1");
            titulo.setAttribute("style", "color:red;");
            titulo.appendChild(document.createTextNode("Seleccione una vuelta."));
            padre.appendChild(titulo);
            return false;
        }
    }
    return true;
}
function borrarViaje() {
    if (document.getElementById("p1")) {
        document.getElementById("p1").parentNode.removeChild(document.getElementById("p1"));
    }
}
function viajes() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var x = "<option value='0'>- Escoja un origen -</option>";
            x += xmlhttp.responseText;
            document.getElementById("selecOrigen").innerHTML = x;
        }
    };
    xmlhttp.open("GET", "../ConseguirAeropuertos", true);
    xmlhttp.send();
}
function viajesDestino(iata) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var x = "<option value='0'>- Escoja un destino -</option>";
            x += xmlhttp.responseText;
            document.getElementById("selecDestino").innerHTML = x;
        }
    };
    xmlhttp.open("GET", "../ConseguirAeropuertosDisponibles?IDiata=" + iata, true);
    xmlhttp.send();
}
function validarDNIPantalla(IDSelect, valor, IDetiqueta) {
    if (document.getElementById(IDSelect).value == "dni") {
        if (validarDNI(valor)) {
            document.getElementById(IDetiqueta).style.background = "#99FF99";
        } else {
            document.getElementById(IDetiqueta).value = "";
            document.getElementById(IDetiqueta).style.background = "white";
            document.getElementById(IDetiqueta).placeholder = "Error DNI invalido.";
        }
    }
    if (document.getElementById(IDSelect).value == "pasaporte") {
        if (validarPASAPORTE(valor)) {
            document.getElementById(IDetiqueta).style.background = "#99FF99";
        } else {
            document.getElementById(IDetiqueta).value = "";
            document.getElementById(IDetiqueta).style.background = "white";
            document.getElementById(IDetiqueta).placeholder = "Error PASAPORTE invalido.";
        }
    }
}
function validarPASAPORTE(pasaporte) {
    if ((/^[A-Z]{3}\d{6}$/.test(pasaporte))) {
        return true;
    }
}
function validarDNI(numIdentidad) {
    var numero, letra;
    var cadena = 'TRWAGMYFPDXBNJZSQVHLCKET';
    var expr = /^[XYZ]?\d{5,8}[A-Z]$/;
    numIdentidad = numIdentidad.toUpperCase();
    if (!expr.test(numIdentidad)) {
        return false;
    }
    numero = numIdentidad.substr(0, numIdentidad.length - 1);
    letra = numIdentidad.substr(numIdentidad.length - 1, 1);
    numero = numero % 23;
    cadena = cadena.substring(numero, numero + 1);
    if (letra != cadena) {
        return false;
    }
    return true;
}
function div(ID) {
    var elemento = document.getElementById(ID);
    if (elemento.getAttribute("style") == "display: none;") {
        elemento.setAttribute("style", "display: inline-block;");
        document.getElementById(ID).style.marginLeft = "10%";
    } else {
        elemento.setAttribute("style", "display: none;");
    }
}

function cambioPrecios(servicio, precio, propioID) {
    if (document.getElementById(servicio)) {
        var total = parseFloat(document.getElementById("totalPagar").value);
//        var total = parseFloat(document.getElementById("totalPagar").textContent);
        var precioServicio = parseFloat(precio);
        var totalP = parseFloat(document.getElementById(servicio + "spanP").textContent);
        if (!document.getElementById(propioID).checked) {
            if (totalP === 1) {
                borrar(servicio);
                //document.getElementById("totalPagar").textContent = ((total - precioServicio).toFixed(2));
                document.getElementById("totalPagar").value = ((total - precioServicio).toFixed(2));
            } else {
                totalP--;
                var ponerPrecio = precioServicio * totalP;
                //document.getElementById("totalPagar").textContent = (total - precioServicio).toFixed(2);
                document.getElementById("totalPagar").value = (total - precioServicio).toFixed(2);
                document.getElementById(servicio + "span").textContent = " pers. = " + ponerPrecio + " €";
                document.getElementById(servicio + "spanP").textContent = totalP;
            }
        } else {
            totalP++;
            var ponerPrecio = precioServicio * totalP;
            document.getElementById(servicio + "span").textContent = " pers. = " + ponerPrecio + " €";
            //document.getElementById("totalPagar").textContent = ((total + precioServicio).toFixed(2));
            document.getElementById("totalPagar").value = ((total + precioServicio).toFixed(2));
            document.getElementById(servicio + "spanP").textContent = totalP;
        }
    } else {
        createELEMENT(servicio, precioServicio);
        cambioPrecios(servicio, precio, propioID);
    }
}
function cambioPreciosV(servicio, precio, propioID) {
    if (document.getElementById(servicio)) {
        var total = parseFloat(document.getElementById("totalPagar").value);
//        var total = parseFloat(document.getElementById("totalPagar").textContent);
        var precioServicio = parseFloat(precio);
        var totalP = parseFloat(document.getElementById(servicio + "spanP").textContent);
        if (!document.getElementById(propioID).checked) {
            if (totalP === 1) {
                borrar(servicio);
//                document.getElementById("totalPagar").textContent = ((total - precioServicio).toFixed(2));
                document.getElementById("totalPagar").value = ((total - precioServicio).toFixed(2));
            } else {
                totalP--;
                var ponerPrecio = precioServicio * totalP;
//                document.getElementById("totalPagar").textContent = (total - precioServicio).toFixed(2);
                document.getElementById("totalPagar").value = (total - precioServicio).toFixed(2);
                document.getElementById(servicio + "span").textContent = " pers. = " + ponerPrecio + " €";
                document.getElementById(servicio + "spanP").textContent = totalP;
            }
        } else {
            totalP++;
            var ponerPrecio = precioServicio * totalP;
            document.getElementById(servicio + "span").textContent = " pers. = " + ponerPrecio + " €";
//            document.getElementById("totalPagar").textContent = ((total + precioServicio).toFixed(2));
            document.getElementById("totalPagar").value = ((total + precioServicio).toFixed(2));
            document.getElementById(servicio + "spanP").textContent = totalP;
        }
    } else {
        createELEMENTV(servicio, precioServicio);
        cambioPreciosV(servicio, precio, propioID);
    }
}
function borrar(Id) {
    if (document.getElementById(Id)) {
        document.getElementById(Id).parentNode.removeChild(document.getElementById(Id));
    }
}
function createELEMENT(ID, precio) {
    var padre = document.getElementById("padreIDA");
    var p = document.createElement("p");
    var span = document.createElement("span");
    var span01 = document.createElement("span");
    p.setAttribute("id", ID);
    span.setAttribute("id", ID + "span");
    span01.setAttribute("id", ID + "spanP");
    p.appendChild(document.createTextNode(ID + " x "));
    span.appendChild(document.createTextNode(precio));
    span01.appendChild(document.createTextNode("0"));
    p.appendChild(span01);
    p.appendChild(span);
    padre.appendChild(p);
}
function createELEMENTV(ID, precio) {
    var padre = document.getElementById("padreVUELTA");
    var p = document.createElement("p");
    var span = document.createElement("span");
    var span01 = document.createElement("span");
    p.setAttribute("id", ID);
    span.setAttribute("id", ID + "span");
    span01.setAttribute("id", ID + "spanP");
    p.appendChild(document.createTextNode(ID + " x "));
    span.appendChild(document.createTextNode(precio));
    span01.appendChild(document.createTextNode("0"));
    p.appendChild(span01);
    p.appendChild(span);
    padre.appendChild(p);
}
//function cambioServicioPrecio(vuelta, IDSalida, valor, propioID) {
//    var total = parseFloat(document.getElementById("totalPagar").innerHTML);
//    var valorNumero = parseFloat(valor);
//    var vuelta = parseFloat(vuelta);
//    if (document.getElementById(propioID).checked) {
//        var anteriorValor = parseFloat(document.getElementById(IDSalida).innerHTML);
//        document.getElementById(IDSalida).innerHTML = anteriorValor + (valorNumero * vuelta);
//        document.getElementById(IDSalida).value = anteriorValor + (valorNumero * vuelta);
//        var x = total + (valorNumero * vuelta);
//        var conDecimal = x.toFixed(2);
//        document.getElementById("totalPagar").innerHTML = conDecimal;
//    } else {
//        var anteriorValor = parseFloat(document.getElementById(IDSalida).innerHTML);
//        document.getElementById(IDSalida).innerHTML = anteriorValor - (valorNumero * vuelta);
//        document.getElementById(IDSalida).value = anteriorValor - (valorNumero * vuelta);
//        var x = total - (valorNumero * vuelta);
//        var conDecimal = x.toFixed(2);
//        document.getElementById("totalPagar").innerHTML = conDecimal;
//    }
//}
//

function validarRegistro() {
    borrarS();
    var dni = document.getElementById("dni").value;
    if (!validarDNI(dni)) {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color:red;");
        titulo.appendChild(document.createTextNode("Introduzca un DNI válido."));
        padre.appendChild(titulo);
        return false;
    }
    var telf = document.getElementById("telf").value;
    if (!(/^\d{9}$/.test(telf))) {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color:red;");
        titulo.appendChild(document.createTextNode("Introduzca bien el teléfono."));
        padre.appendChild(titulo);
        return false;
    }
    var cp = document.getElementById("cp").value;
    if (!(/^\d{5}$/.test(cp))) {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color:red;");
        titulo.appendChild(document.createTextNode("Introduzca bien el código postal."));
        padre.appendChild(titulo);
        return false;
    }
    var correo = document.getElementById("correoU").value;
    if (!(/^[a-z0-9._%+-]+\@[a-z0-9.-]+\.[a-z]{2,4}$/.test(correo))) {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color:red;");
        titulo.appendChild(document.createTextNode("Introduzca bien el correo."));
        padre.appendChild(titulo);
        return false;
    }
    var pas00 = document.getElementById("pass00").value;
    var pas01 = document.getElementById("pass01").value;
    if (pas00 != pas01) {
        borrarS();
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id", "p1");
        titulo.setAttribute("style", "color:red;");
        titulo.appendChild(document.createTextNode("No son iguales las contraseñas."));
        padre.appendChild(titulo);
        return false;
    }
}
function ObtenerUserIndex() {
    var pass = document.getElementById("pass").value;
    var correo = document.getElementById("correo").value;
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var x = xmlhttp.responseText;
            if (x == "no") {
                alert("Usuario no registrado.");
            } else {
                comprobarCajonI();
                document.getElementById("prueba").textContent = x;
            }
        }
    };
    xmlhttp.open("GET", "./accesoSistema?pass=" + pass + "&correo=" + correo, true);
    xmlhttp.send();
}
function ObtenerUser() {
    var pass = document.getElementById("pass").value;
    var correo = document.getElementById("correo").value;
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var x = xmlhttp.responseText;
            if (x == "no") {
                alert("Usuario no registrado.");
            } else {
                comprobarCajon();
                document.getElementById("prueba").textContent = x;
            }
        }
    };
    xmlhttp.open("GET", "../accesoSistema?pass=" + pass + "&correo=" + correo, true);
    xmlhttp.send();
}
function comprobarCajonI() {
    if (document.getElementById("cajon")) {
        document.getElementById("cajon").parentNode.removeChild(document.getElementById("cajon"));
    }
    var padre = document.getElementById("acceso");
    var ul = document.createElement("ul");
    ul.setAttribute("id", "cajon");
    var li = document.createElement("li");
    var p = document.createElement("p");
    var a = document.createElement("a");
    a.setAttribute("href", "./DispatcherAcceso?control=Desconectar");
    a.appendChild(document.createTextNode("Desconectar"));
    p.appendChild(a);
    li.appendChild(p);
    ul.appendChild(li);
    padre.appendChild(ul);
    return true;
}
function comprobarCajon() {
    if (document.getElementById("cajon")) {
        document.getElementById("cajon").parentNode.removeChild(document.getElementById("cajon"));
    }
    var padre = document.getElementById("acceso");
    var ul = document.createElement("ul");
    ul.setAttribute("id", "cajon");
    var li = document.createElement("li");
    var p = document.createElement("p");
    var a = document.createElement("a");
    a.setAttribute("href", "../DispatcherAcceso?control=Desconectar");
    a.appendChild(document.createTextNode("Desconectar"));
    p.appendChild(a);
    li.appendChild(p);
    ul.appendChild(li);
    padre.appendChild(ul);
    return true;
}
function ponerAsiento(valor) {
    document.getElementById("asientoPasaj").innerHTML = valor;
    document.getElementById("asientoPasaj").value = valor;
}
function ponerAsientoV(valor) {
    document.getElementById("VasientoPasaj").innerHTML = valor;
    document.getElementById("VasientoPasaj").value = valor;
}
function cambio(id, valor) {
    if (document.getElementById(id).alt == "libre") {
        document.getElementById(id).src = "../Imagenes/selected_seat_img.gif";
        document.getElementById(id).alt = "seleccionada";
        poner(id);
        document.getElementById("asientoFinal").innerHTML = "" + valor;
        document.getElementById("asientoFinal").value = "" + valor;
    } else {
        if (document.getElementById(id).alt != "ocupada") {
            document.getElementById(id).src = "../Imagenes/available_seat_img.gif";
            document.getElementById(id).alt = "libre";
            document.getElementById("asientoFinal").value = "";
            document.getElementById("asientoFinal").innerHTML = "";
        }
    }
}
function cambioV(id, valor) {
    if (document.getElementById(id).alt == "libre") {
        document.getElementById(id).src = "../Imagenes/selected_seat_img.gif";
        document.getElementById(id).alt = "seleccionada";
        poner(id);
        document.getElementById("VasientoFinal").innerHTML = "" + valor;
        document.getElementById("VasientoFinal").value = "" + valor;
    } else {
        if (document.getElementById(id).alt != "ocupada") {
            document.getElementById(id).src = "../Imagenes/available_seat_img.gif";
            document.getElementById(id).alt = "libre";
            document.getElementById("VasientoFinal").value = "";
            document.getElementById("VasientoFinal").innerHTML = "";
        }
    }
}
function poner(x) {
    for (var i = 0; i < document.images.length; i++) {
        var id = document.images[i].id;
        if (id != "portada") {
            if (id != x && document.getElementById(id).alt != "ocupada") {
                document.getElementById(id).src = "../Imagenes/available_seat_img.gif";
                document.getElementById(id).alt = "libre";
            }
        }
    }
}
function ponerT() {
    for (var i = 0; i < document.images.length; i++) {
        var id = document.images[i].id;
        if (id != "portada") {
            if (id[0] != "V") {
                if (document.getElementById(id).alt == "seleccionada") {
                    document.getElementById(id).src = "../Imagenes/available_seat_img.gif";
                    document.getElementById(id).alt = "libre";
                }
            }
        }
    }
}
function ponerTV() {
    for (var i = 0; i < document.images.length; i++) {
        var id = document.images[i].id;
        if (id != "portada") {
            if (id[0] == "V") {
                if (document.getElementById(id).alt == "seleccionada") {
                    document.getElementById(id).src = "../Imagenes/available_seat_img.gif";
                    document.getElementById(id).alt = "libre";
                }
            }
        }
    }
}
function validarAsientoIDA() {
    var ocupacion = document.getElementById("asientoFinal").innerHTML;
    if (ocupacion != "") {
        if (!RecorrerForm(ocupacion)) {
            var id = document.getElementById("asientoPasaj").innerHTML;
            document.getElementById(id).value = "" + ocupacion;
            document.getElementById(id).innerHTML = "" + ocupacion;
            document.getElementById("asientoFinal").value = "";
            document.getElementById("asientoFinal").innerHTML = "";
            document.getElementById("aref").click();
            borrar("escelar");
            ponerT();
            return true;
        } else {
            crearSalidaMSG("Esa ocupación esta ocupada.");
            return false;
        }
    } else {
        crearSalidaMSG("Escoge una ocupación valida.");
        return false;
    }
}
function validarAsientoV() {
    var ocupacion = document.getElementById("VasientoFinal").innerHTML;
    if (ocupacion != "") {
        if (!RecorrerFormV(ocupacion)) {
            var id = document.getElementById("VasientoPasaj").innerHTML;
            document.getElementById(id).value = "" + ocupacion;
            document.getElementById(id).innerHTML = "" + ocupacion;
            document.getElementById("VasientoFinal").value = "";
            document.getElementById("VasientoFinal").innerHTML = "";
            document.getElementById("arefV").click();
            borrar("escelar");
            ponerTV();
            return true;
        } else {
            crearSalidaMSGV("Esa ocupación esta ocupada.");
            return false;
        }
    } else {
        crearSalidaMSGV("Escoge una ocupación valida.");
        return false;
    }
}
function crearSalidaMSG(msg) {
    borrar("escelar");
    var padre = document.getElementById("salidaMSG");
    var p = document.createElement("p");
    p.setAttribute("id", "escelar");
    p.appendChild(document.createTextNode(msg));
    padre.appendChild(p);
}
function crearSalidaMSGV(msg) {
    borrar("escelar");
    var padre = document.getElementById("salidaMSGV");
    var p = document.createElement("p");
    p.setAttribute("id", "escelar");
    p.appendChild(document.createTextNode(msg));
    padre.appendChild(p);
}
function RecorrerForm(posicion) {
    var frm = document.getElementById("formula");
    for (var i = 0; i < frm.elements.length; i++) {
        if (frm.elements[i].id[0] != "V") {
            if (frm.elements[i].value == posicion) {
                return true;
            }
        }
    }
    return false;
}
function RecorrerFormV(posicion) {
    var frm = document.getElementById("formula");
    for (var i = 0; i < frm.elements.length; i++) {
        if (frm.elements[i].id[0] == "V") {
            if (frm.elements[i].value == posicion) {
                return true;
            }
        }
    }
    return false;
}
function ObtenerFilasIDA(IDVuelo) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var x = xmlhttp.responseText;
            for (var i = 0; i < x.length; i = i + 5) {
                var salida = x[i] + x[i + 1] + x[i + 2];
                document.getElementById("asiento" + salida).src = "../Imagenes/available_seat_img.gif";
                document.getElementById("asiento" + salida).alt = "libre";
            }
        }
    };
    xmlhttp.open("GET", "../ConseguirFilasAsiento?IDVuelo=" + IDVuelo, true);
    xmlhttp.send();
}
function ObtenerFilasV(IDVuelo) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var x = xmlhttp.responseText;
            for (var i = 0; i < x.length; i = i + 5) {
                var salida = x[i] + x[i + 1] + x[i + 2];
                document.getElementById("Vasiento" + salida).src = "../Imagenes/available_seat_img.gif";
                document.getElementById("Vasiento" + salida).alt = "libre";
            }
        }
    };
    xmlhttp.open("GET", "../ConseguirFilasAsiento?IDVuelo=" + IDVuelo, true);
    xmlhttp.send();
}
function mostrarRegistro() {
    document.getElementById("RegistrarFormulario").style.display = "inline-block";
    document.getElementById("RegistrarFormulario").style.marginLeft = "21%";
}
function eleccionTarjeta() {
    var contenido = document.getElementById("numeroTarjeta");
    if (contenido.value.length === 4) {
        contenido.value = contenido.value + " ";
    }
    if (contenido.value.length === 9) {
        contenido.value = contenido.value + " ";
    }
    if (contenido.value.length === 14) {
        contenido.value = contenido.value + " ";
    }
    var tipo = document.getElementById("numeroTarjeta").value[0];
    var tipo1 = document.getElementById("numeroTarjeta").value[1];
    if (tipo == "5") {
        if (tipo1 >= 1 && tipo1 <= 5) {
            document.getElementById("imgTarjeta").src = "../Imagenes/mastercard.png";
            document.getElementById("imgTarjeta").alt = "MasterCard";
            document.getElementById("tipoT").value = "MasterCard";
            return true;
        }
    }
    if (tipo == "4") {
        document.getElementById("imgTarjeta").src = "../Imagenes/visa.png";
        document.getElementById("imgTarjeta").alt = "Visa";
        document.getElementById("tipoT").value = "Visa";
        return true;
    }
    if (tipo == "3") {
        document.getElementById("imgTarjeta").src = "../Imagenes/americanexpress.png";
        document.getElementById("imgTarjeta").alt = "American Express";
        document.getElementById("tipoT").value = "American Express";
        return true;
    }
    document.getElementById("tipoT").value = "-1";
    document.getElementById("imgTarjeta").src = "";
    document.getElementById("imgTarjeta").alt = "";
}
var click = 1;
function mostrarTarjetas() {
    if (click == 1) {
        document.getElementById("crearTarjeta").style.display = "inline-block";
        click = 0;
    } else {
        document.getElementById("crearTarjeta").style.display = "none";
        click = 1;
    }
}
function validarTarjeta() {
    if (document.getElementById("numeroTarjeta").value.length != 19) {
        alert("Faltan números de la tarjeta.");
        return false;
    }
    if (document.getElementById("numeroTarjeta").value[0] != 3 && document.getElementById("numeroTarjeta").value[0] != 4 && document.getElementById("numeroTarjeta").value[0] != 5) {
        alert("Tarjeta no valida.");
        return false;
    }
    var numeros = document.getElementById("numeroTarjeta").value.split(' ');
    var total = "";
    for (var i = 0; i < numeros.length; i++) {
        var cuatroN = numeros[i];
        total = total + cuatroN;
    }
    if (isNaN(total)) {
        document.getElementById("numeroTarjeta").value = "";
        alert("Tarjeta no valida.");
        return false;
    }
    if (!Check(total)) {
        alert("Tarjeta no valida.");
        return false;
    }
    return true;
}
function Check(value) {
    if (/[^0-9-\s]+/.test(value))
        return false;
    var nCheck = 0, nDigit = 0, bEven = false;
    value = value.replace(/\D/g, "");
    for (var n = value.length - 1; n >= 0; n--) {
        var cDigit = value.charAt(n),
                nDigit = parseInt(cDigit, 10);
        if (bEven) {
            if ((nDigit *= 2) > 9)
                nDigit -= 9;
        }
        nCheck += nDigit;
        bEven = !bEven;
    }
    return (nCheck % 10) == 0;
}

function addTarjeta() {
    var tipo = document.getElementById("tipoT").value;
    var numeros = document.getElementById("numeroTarjeta").value.split(' ');
    var total = "";
    for (var i = 0; i < numeros.length; i++) {
        var cuatroN = numeros[i];
        total = total + cuatroN;
    }
    var numero = total;
    var csv = document.getElementById("csvTarjeta").value;
    var mes = document.getElementById("mesTarjeta").value;
    var year = document.getElementById("anoTarjeta").value;
    var user = document.getElementById("prueba").textContent;
    document.getElementById("addTarjeta").style.display = "none";
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var id = xmlhttp.responseText;
            if (id != "no") {
                var padre = document.getElementById("tarjetasTabla");
                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                if (tipo == "MasterCard") {
                    var img = document.createElement("img");
                    img.setAttribute("src", "../Imagenes/mastercard.png");
                    img.setAttribute("alt", "master");
                    td1.appendChild(img);
                }
                if (tipo == "Visa") {
                    var img = document.createElement("img");
                    img.setAttribute("src", "../Imagenes/visa.png");
                    img.setAttribute("alt", "visa");
                    td1.appendChild(img);
                }
                if (tipo == "American Express") {
                    var img = document.createElement("img");
                    img.setAttribute("src", "../Imagenes/americanexpress.png");
                    img.setAttribute("alt", "visa");
                    td1.appendChild(img);
                }
                tr.appendChild(td1);
                var td2 = document.createElement("td");
                td2.appendChild(document.createTextNode("*" + numero.substring(12, 16)));
                tr.appendChild(td2);
                var td3 = document.createElement("td");
                td3.appendChild(document.createTextNode(mes + "/" + year));
                tr.appendChild(td3);
                var td4 = document.createElement("td");
                var input = document.createElement("input");
                input.setAttribute("type", "radio");
                input.setAttribute("id", "tarjeta" + id);
                input.setAttribute("value", id);
                input.setAttribute("name", "tarjeta");
                td4.appendChild(input);
                tr.appendChild(td4);
                padre.appendChild(tr);
            } else {
                alert(0);
            }
            document.getElementById("crearTarjeta").style.display = "none";
            click = 1;
        }
    };
    xmlhttp.open("GET", "../addTarjeta?tipo=" + tipo + "&numero=" + numero + "&csv=" + csv + "&mes=" + mes + "&year=" + year + "&user=" + user, true);
    xmlhttp.send();
}
function validarReserva() {
    if (document.getElementsByName("tarjeta")) {
        var opciones = document.getElementsByName("tarjeta");
        var seleccionado = false;
        for (var i = 0; i < opciones.length; i++) {
            if (opciones[i].checked) {
                seleccionado = true;
                break;
            }
        }
        if (seleccionado) {
            return true;
        }
    } else {
        alert("Añada una tarjeta");
        return false;
    }
    return false;
}
function numTelefono(pais) {
    if (pais == "Es") {
        document.getElementById("telf").value = "+34 ";
        return true;
    }
    document.getElementById("telf").value = "";
}
function validarnNacio(nacionalidad, id, idContenido) {
    if (nacionalidad == "OTRA") {
        document.getElementById(id).value = "pasaporte";
        document.getElementById(idContenido).value = "";
        return true;
    }
}