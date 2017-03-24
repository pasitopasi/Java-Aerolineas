/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Aeropuerto;
import Modelo.Avion;
import Modelo.Cliente;
import Modelo.ConexionAreopuertos;
import Modelo.Estadistica;
import Modelo.Pasajero;
import Modelo.Posicion;
import Modelo.Reserva;
import Modelo.Servicio;
import Modelo.ServicioEstadistico;
import Modelo.Tarjeta;
import Modelo.Vuelo;
import Modelo.arrayPosiciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author pasito
 */
public class Operaciones {

    public ArrayList<Aeropuerto> obtenerAeropuertos(Connection con) {
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();

        String sql = "SELECT * FROM aeropuerto";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String iata = resultado.getString("IATA");
                String nombre = resultado.getString("Nombre");
                String localidad = resultado.getString("Localidad");
                int capacidad = resultado.getInt("Capacidad");

                Aeropuerto a = new Aeropuerto(iata, nombre, localidad, capacidad);
                a.setID(id);

                aeropuertos.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return aeropuertos;
    }

    public ArrayList<Aeropuerto> obtenerAeropuertosDisponibles(Connection con, String idiataOrigen) {
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();

        String sql = "select * from aeropuerto where ID in (select ID_Destino from conexion where ID_Origen = ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, idiataOrigen);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String iata = resultado.getString("IATA");
                String nombre = resultado.getString("Nombre");
                String localidad = resultado.getString("Localidad");
                int capacidad = resultado.getInt("Capacidad");

                Aeropuerto a = new Aeropuerto(iata, nombre, localidad, capacidad);
                a.setID(id);

                aeropuertos.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return aeropuertos;
    }

    public ArrayList<Vuelo> obtenerVuelos(Connection con, ConexionAreopuertos vuelo, String fechaVuelo) {
        ArrayList<Vuelo> vuelos = new ArrayList<>();

        String sql = "select * from vuelo where ID_Conexion = (select ID from conexion where ID_Origen=? and ID_Destino=?) and Fecha BETWEEN DATE_SUB(?,INTERVAL 1 WEEK) AND DATE_ADD(?,INTERVAL 1 WEEK)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, vuelo.getID_Origen());
            sentencia.setString(2, vuelo.getID_Destino());
            sentencia.setString(3, fechaVuelo);
            sentencia.setString(4, fechaVuelo);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                int ID = resultado.getInt("ID");
                int IDConexion = resultado.getInt("ID_Conexion");
                String CODVuelo = resultado.getString("COD_Vuelo");
                LocalDate fecha = resultado.getDate("Fecha").toLocalDate();
                Time Hora_Salida = resultado.getTime("Hora_Salida");
                Time Hora_Entrada = resultado.getTime("Hora_Entrada");
                double precio = resultado.getDouble("Precio");
                Time duracion = resultado.getTime("Duracion");
                int plazas = resultado.getInt("Plazas_Libres");
                int avion = resultado.getInt("Avion");

                Vuelo v = new Vuelo(IDConexion, CODVuelo, fecha, Hora_Salida, Hora_Entrada, precio, duracion, plazas);
                v.setID(ID);
                v.setAvion(avion);

                vuelos.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }

        return vuelos;
    }

    public ArrayList<Servicio> obtenerServicios(Connection con) {
        ArrayList<Servicio> servicios = new ArrayList<>();

        String sql = "SELECT * FROM servicio order by ID asc";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String servicio = resultado.getString("Servicio");
                double precio = resultado.getDouble("Precio");

                Servicio s = new Servicio(servicio, precio);
                s.setID(id);

                servicios.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return servicios;
    }

    public String obtenerAeropuerto(Connection con, String ID) {
        String aeropuerto = "";
        String sql = "select * from aeropuerto where ID = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, ID);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String iata = resultado.getString("IATA");
                String nombre = resultado.getString("Localidad");

                aeropuerto = nombre + " (" + iata + ")";
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return aeropuerto;
    }

    public ArrayList<String> obtenerAsientosLibres(Connection con, String fila, String IDVuelo) {
        ArrayList<String> asientos = new ArrayList<>();
        String sql = "select columna from ocupacion where ( (IDVuelo=?) AND (IDPasajero is null) AND (fila=?))";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, IDVuelo);
            sentencia.setString(2, fila);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String columna = resultado.getString("columna");
                asientos.add(columna);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return asientos;
    }

    public ArrayList<String> obtenerOcupacion(Connection con, String IDVuelo) {
        ArrayList<String> filas = new ArrayList<>();
        String sql = "select fila, columna from ocupacion where IDVuelo=? and IDPasajero is null order by fila, columna ASC";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, IDVuelo);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String fila = resultado.getString("Fila");
                String Columna = resultado.getString("Columna");
                filas.add(fila + "-" + Columna);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return filas;
    }

    public Avion ObtenerAvion(Connection con, int IDAvion) {

        String sql = "select * from avion where id=?";
        PreparedStatement sentencia;
        Avion av = null;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, IDAvion);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int Filas = resultado.getInt("Filas");
                int Columnas = resultado.getInt("Columnas");
                int Plazas = resultado.getInt("Plazas");
                String COD_Avion = resultado.getString("COD_Avion");
                String Tipo = resultado.getString("Tipo");

                av = new Avion(COD_Avion, Tipo, Plazas, Filas, Columnas);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return av;
    }

    public String comprobarCliente(Connection con, String pass, String correo) {

        String sql = "select * from cliente where (Correo = ? and AES_DECRYPT(Contrasena, 'semilla') = ?) or (Usuario = ? and AES_DECRYPT(Contrasena, 'semilla')=?)";
        PreparedStatement sentencia;

        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, correo);
            sentencia.setString(2, pass);
            sentencia.setString(3, correo);
            sentencia.setString(4, pass);

            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                String UsuarioN = resultado.getString("Usuario");
                return UsuarioN;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return ex.getMessage();
        }
        return "";
    }

    public ArrayList<Tarjeta> obtenerTarjetas(Connection con, String usuario) {
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();

        String sql = "select ID, ID_Cliente, SUBSTRING(AES_DECRYPT(numero, 'semilla'),13) as 'numero', Tipo, CSV, Mes_Caducidad, Ano_Caducidad from tarjeta where ID_Cliente = (select ID from cliente where Usuario=?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, usuario);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                int id_cliente = resultado.getInt("ID_Cliente");
                String numero = resultado.getString("numero");
                String tipo = resultado.getString("Tipo");
                String csv = resultado.getString("CSV");
                String mesCaducidad = resultado.getString("Mes_Caducidad");
                String anoCaducidad = resultado.getString("Ano_Caducidad");

                Tarjeta t = new Tarjeta(id_cliente, numero, tipo, csv, mesCaducidad, anoCaducidad);
                t.setID(id);

                tarjetas.add(t);
            }
        } catch (SQLException ex) {
            return null;
        }
        return tarjetas;
    }

    private int introducirTarjeta(Connection con, Reserva reserva, int idCliente) throws SQLException {
        String sql = "insert into tarjeta values( null, ?, AES_ENCRYPT(?, 'semilla'), ?, ?, ?, ? )";
        PreparedStatement sentencia;
        Tarjeta tarjeta = reserva.getTarjeta();
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, idCliente);
            sentencia.setString(2, tarjeta.getNumero());
            sentencia.setString(3, tarjeta.getTipo());
            sentencia.setString(4, tarjeta.getCSV());
            sentencia.setString(5, tarjeta.getMesCaducidad());
            sentencia.setString(6, tarjeta.getAñoCaducidad());
            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
            /**
             * Esta parte buscará las posibles llaves generadas automaticamente
             * y nos la devolvera.
             */
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
        throw new SQLException();
    }

    private int idCliente(Connection con, String usuario) throws SQLException {
        String sql = "select ID from cliente where Usuario=?";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, usuario);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                return resultado.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
        throw new SQLException();
    }

    public String reservar(Connection con, Reserva reserva) throws SQLException {
        try {
            con.setAutoCommit(false);
            /* Introducir cliente */
            int idTarjeta, idCliente;
            if (reserva.getCliente() != null) {
                idCliente = insertarCliente(con, reserva);
            } else {
                String usuario = reserva.getUsuario();
                idCliente = idCliente(con, usuario);
            }
            /* Introducir tarjeta */
            // quiere decir que no habia ninguna tarjeta y se introduce a la base de datos
            if (reserva.getIdTarjeta() == 0) {
                idTarjeta = introducirTarjeta(con, reserva, idCliente);
            } else {
                idTarjeta = reserva.getIdTarjeta();
            }
            /* Introduzco la reserva */
            int IDReserva = introducirReserva(con, reserva, idTarjeta, idCliente);
            /* Introduzco pasajeros, servicios y ocupación */
            if (!introducirPasajeros(con, reserva, IDReserva)) {
                throw new SQLException();
            }
            /* Obtenemos el codigo de la reserva para montar el PDF */
            String codigoReserva = ObtenerCODReserva(con, IDReserva);
            /* Actualizamos el número de asientos libres */
            actualizarAsientosIda(con, reserva);
            if (reserva.getVueloVuelta() != null) {
                actualizarAsientosVuelta(con, reserva);
            }
            con.commit();
            return codigoReserva;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                    return "";
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                    throw new SQLException(ex1);
                }
            }
            return "";
        }
    }

    private int introducirReserva(Connection con, Reserva reserva, int idTarjeta, int idCliente) throws SQLException {
        String sql = "insert into reserva values(null, ?, ?, ?, ?, curdate(), ?, ?, null, null)";
        PreparedStatement sentencia;
        String codRes = CODReserva();
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, "RES" + codRes);
            sentencia.setInt(2, idCliente);
            sentencia.setInt(3, reserva.getVueloIda().getID());
            if (reserva.getVueloVuelta() != null) {
                sentencia.setInt(4, reserva.getVueloVuelta().getID());
            } else {
                sentencia.setString(4, null);
            }
            sentencia.setInt(5, idTarjeta);
            sentencia.setDouble(6, reserva.getaPagar());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private String CODReserva() {
        int n1 = (int) (Math.random() * (90 - 65) + 65);
        char s1 = (char) n1;
        int n2 = (int) (Math.random() * (90 - 65) + 65);
        char s2 = (char) n2;
        int n3 = (int) (Math.random() * (90 - 65) + 65);
        char s3 = (char) n3;
        int n4 = (int) (Math.random() * (90 - 65) + 65);
        char s4 = (char) n4;
        int n5 = (int) (Math.random() * (90 - 65) + 65);
        char s5 = (char) n5;
        int num = (int) (Math.random() * (9999 - 1000) + 1000);
        return "" + num + s1 + s2 + s3 + s4 + s5;
    }

    private int insertarCliente(Connection con, Reserva reserva) throws SQLException {
        String sql = "INSERT INTO cliente values(null, ?, ?, ?, ?, ?, ?, ?, ?, AES_ENCRYPT(?, 'semilla'), ?)";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, reserva.getCliente().getTratamiento());
            sentencia.setString(2, reserva.getCliente().getNombre());
            sentencia.setString(3, reserva.getCliente().getApellido());
            sentencia.setString(4, reserva.getCliente().getTipoIden());
            sentencia.setString(5, reserva.getCliente().getDNI());
            sentencia.setString(6, reserva.getCliente().getDireccion());
            sentencia.setString(7, reserva.getCliente().getCorreo());
            sentencia.setString(8, reserva.getCliente().getUsuario());
            sentencia.setString(9, reserva.getCliente().getPassword());
            sentencia.setString(10, reserva.getCliente().getTelf());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    public boolean introducirPasajeros(Connection con, Reserva reserva, int IDReserva) throws SQLException {
        int pasaj = 0;
        /* Almacenare el id de cada pasajero adulto,
           ya que en su momento debere de actualizar sus datos para decir que son
        tutores :)*/
        ArrayList<Integer> idPasajeros = new ArrayList<>();
        for (int idx = 0; idx < reserva.getNadultos(); idx++) {
            int idA = introducirAdulto(con, reserva.getPasajero(pasaj), IDReserva);
            idPasajeros.add(idA);
            /* Aqui introducimos los servicios de IDA */
            Iterator<Servicio> itS = reserva.getPasajero(pasaj).getServiciosIDA().iterator();
            while (itS.hasNext()) {
                Servicio ser = itS.next();
                /* Introducimos los servicios */
                introducirServicio(con, ser, idA, reserva.getVueloIda().getID());
                if (ser.getServicio().equals("Reservar asiento")) {
                    /* Ocupacion de ida a introducir */
                    introducirOcupacion(con, reserva.getPasajero(pasaj).getAsientoI(), reserva.getVueloIda().getID(), idA);
                }
            }
            if (reserva.getVueloVuelta() != null) {
                /* aqui los de vuelta */
                Iterator<Servicio> itV = reserva.getPasajero(pasaj).getServiciosVUELTA().iterator();
                while (itV.hasNext()) {
                    Servicio ser = itV.next();
                    /* Introducimos los servicios */
                    introducirServicio(con, ser, idA, reserva.getVueloVuelta().getID());
                    if (ser.getServicio().equals("Reservar asiento")) {
                        /* Ocupacion de vuelta a introducir */
                        introducirOcupacion(con, reserva.getPasajero(pasaj).getAsientoV(), reserva.getVueloVuelta().getID(), idA);
                    }
                }
            }

            pasaj++;
        }
        for (int idx = 0; idx < reserva.getNninos(); idx++) {
            int idArray = Integer.parseInt(reserva.getPasajero(pasaj).getID_tutor());
            int idTutor = idPasajeros.get(idArray);
            int idB = introducirMenor(con, reserva.getPasajero(pasaj), IDReserva, idTutor);
            /* Aqui introducimos los servicios de IDA */
            Iterator<Servicio> itS = reserva.getPasajero(pasaj).getServiciosIDA().iterator();
            while (itS.hasNext()) {
                Servicio ser = itS.next();
                /* Introducimos los servicios */
                introducirServicio(con, ser, idB, reserva.getVueloIda().getID());
                if (ser.getServicio().equals("Reservar asiento")) {
                    /* Ocupacion de ida a introducir */
                    introducirOcupacion(con, reserva.getPasajero(pasaj).getAsientoI(), reserva.getVueloIda().getID(), idB);
                }
            }
            if (reserva.getVueloVuelta() != null) {
                /* aqui los de vuelta */
                Iterator<Servicio> itV = reserva.getPasajero(pasaj).getServiciosVUELTA().iterator();
                while (itV.hasNext()) {
                    Servicio ser = itV.next();
                    /* Introducimos los servicios */
                    introducirServicio(con, ser, idB, reserva.getVueloVuelta().getID());
                    if (ser.getServicio().equals("Reservar asiento")) {
                        /* Ocupacion de vuelta a introducir */
                        introducirOcupacion(con, reserva.getPasajero(pasaj).getAsientoV(), reserva.getVueloVuelta().getID(), idB);
                    }
                }
            }
            /* actualizamos a los adultos que son tutores */
            actualizarTutor(con, idTutor);
            pasaj++;
        }
        for (int idx = 0; idx < reserva.getNbebes(); idx++) {
            int idArray = Integer.parseInt(reserva.getPasajero(pasaj).getID_tutor());
            int idTutor = idPasajeros.get(idArray);
            introducirBebe(con, reserva.getPasajero(pasaj), IDReserva, idTutor);
            /* actualizamos a los adultos que son tutores */
            actualizarTutor(con, idTutor);
            pasaj++;
        }
        return true;
    }

    private int introducirAdulto(Connection con, Pasajero pasajero, int IDReserva) throws SQLException {
        String sql = "INSERT INTO pasajero values(null, ?, ?, ?, ?, ?, ?, ?, ?, null, null, ?)";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, pasajero.getTratamiento());
            sentencia.setString(2, pasajero.getNombre());
            sentencia.setString(3, pasajero.getApellidos());
            sentencia.setString(4, pasajero.getTipoIden());
            sentencia.setString(5, pasajero.getIdentificador());
            sentencia.setString(6, pasajero.getFecha_caducidad().toString());
            sentencia.setString(7, pasajero.getFecha_nacimiento().toString());
            sentencia.setString(8, pasajero.getNacionalidad());
            sentencia.setInt(9, IDReserva);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private boolean introducirServicio(Connection con, Servicio servicio, int idPasajero, int IDvuelo) throws SQLException {
        String sql = "INSERT INTO pasajeroservicio values(null, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idPasajero);
            sentencia.setInt(2, servicio.getID());
            sentencia.setInt(3, IDvuelo);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean introducirOcupacion(Connection con, String ocupacion, int idVuelo, int idPasajero) throws SQLException {
        String sql = "UPDATE ocupacion set IDPasajero=? where IDVuelo=? and Fila=? and Columna=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idPasajero);
            sentencia.setInt(2, idVuelo);
            sentencia.setString(3, "" + ocupacion.charAt(0));
            sentencia.setString(4, "" + ocupacion.charAt(2));

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    private int introducirMenor(Connection con, Pasajero pasajero, int IDReserva, int idTutor) throws SQLException {
        String sql = "INSERT INTO pasajero values(null, ?, ?, ?, ?, ?, ?, ?, ?, null, ?, ?)";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, pasajero.getTratamiento());
            sentencia.setString(2, pasajero.getNombre());
            sentencia.setString(3, pasajero.getApellidos());
            sentencia.setString(4, pasajero.getTipoIden());
            sentencia.setString(5, pasajero.getIdentificador());
            sentencia.setString(6, pasajero.getFecha_caducidad().toString());
            sentencia.setString(7, pasajero.getFecha_nacimiento().toString());
            sentencia.setString(8, pasajero.getNacionalidad());
            sentencia.setInt(9, idTutor);
            sentencia.setInt(10, IDReserva);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private int introducirBebe(Connection con, Pasajero pasajero, int IDReserva, int idTutor) throws SQLException {
        String sql = "INSERT INTO pasajero values(null, null, ?, ?, ?, ?, ?, ?, ?, null, ?, ?)";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, pasajero.getNombre());
            sentencia.setString(2, pasajero.getApellidos());
            sentencia.setString(3, pasajero.getTipoIden());
            sentencia.setString(4, pasajero.getIdentificador());
            sentencia.setString(5, pasajero.getFecha_caducidad().toString());
            sentencia.setString(6, pasajero.getFecha_nacimiento().toString());
            sentencia.setString(7, pasajero.getNacionalidad());
            sentencia.setInt(8, idTutor);
            sentencia.setInt(9, IDReserva);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private int actualizarTutor(Connection con, int idTutor) throws SQLException {
        //update pasajero set Tutor='tutor' where ID='2'
        String sql = "update pasajero set Tutor=? where ID=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, "si");
            sentencia.setInt(2, idTutor);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return 1;
    }

    private String ObtenerCODReserva(Connection con, int idReserva) throws SQLException {
        String sql = "select COD_Reserva from reserva where ID=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idReserva);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                return resultado.getString("COD_Reserva");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
        throw new SQLException();
    }

    private boolean actualizarAsientosIda(Connection con, Reserva reserva) throws SQLException {
        String sql = "UPDATE vuelo set Plazas_Libres=Plazas_Libres-? where ID=?";
        PreparedStatement sentencia;
        try {
            int suma = reserva.getNadultos() + reserva.getNninos();
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, suma);
            sentencia.setInt(2, reserva.getVueloIda().getID());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean actualizarAsientosVuelta(Connection con, Reserva reserva) throws SQLException {
        String sql = "UPDATE vuelo set Plazas_Libres=Plazas_Libres-? where ID=?";
        PreparedStatement sentencia;
        try {
            int suma = reserva.getNadultos() + reserva.getNninos();
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, suma);
            sentencia.setInt(2, reserva.getVueloVuelta().getID());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    /**
     * Con esto obtengo los ID de pasajeros que son de esa reserva que no tienen
     * asiento asignado.
     *
     * @param con
     * @param reserva
     * @return
     */
    private ArrayList<Pasajero> obtenerPasajeros(Connection con, Reserva reserva) {
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        String sql = "select ID from pasajero where ID_Reserva=(select ID from reserva where COD_Reserva=?) and Tratamiento is not null and ID not in (select IDPasajero from ocupacion where  IDPasajero is not null and IDVuelo=(select ID_Vuelo_Ida from reserva where COD_Reserva=?))";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, reserva.getCOD_Reserva());
            sentencia.setString(2, reserva.getCOD_Reserva());

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                Pasajero p = new Pasajero();
                p.setID(id);
                pasajeros.add(p);
            }
        } catch (SQLException ex) {
            return null;
        }
        return pasajeros;
    }

    private ArrayList<Pasajero> obtenerPasajerosV(Connection con, Reserva reserva) {
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        String sql = "select ID from pasajero where ID_Reserva=(select ID from reserva where COD_Reserva=?) and Tratamiento is not null and ID not in (select IDPasajero from ocupacion where  IDPasajero is not null and IDVuelo=(select ID_Vuelo_Vuelta from reserva where COD_Reserva=?))";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, reserva.getCOD_Reserva());
            sentencia.setString(2, reserva.getCOD_Reserva());

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                Pasajero p = new Pasajero();
                p.setID(id);
                pasajeros.add(p);
            }
        } catch (SQLException ex) {
            return null;
        }
        return pasajeros;
    }

    /**
     * Obtendre la Reserva para poder llamar a lo de los pasajeros, 
     * tambien meteré los id de los vuelos 
     * @param con
     * @param CODReserva
     * @param correo
     * @return 
     */
    public Reserva obtenerReserva(Connection con, String CODReserva, String correo) {

        String sql = "select * from reserva where COD_Reserva=? and ID_Cliente = (select ID from cliente where correo=?) ";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, CODReserva);
            sentencia.setString(2, correo);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int ID_Vuelo_Ida = resultado.getInt("ID_Vuelo_Ida");
                String ID_Vuelo_Vue = resultado.getString("ID_Vuelo_Vuelta");
                String facturacionI = resultado.getString("FacturarI");
                String facturacionV = resultado.getString("FacturarV");

                Reserva r = new Reserva(CODReserva);
                r.setFacturacionIda(facturacionI);
                r.setFacturacionVuelta(facturacionV);
                Vuelo ida = new Vuelo();
                ida.setID(ID_Vuelo_Ida);
                r.setVueloIda(ida);
                if (ID_Vuelo_Vue != null) {
                    Vuelo vuelta = new Vuelo();
                    int idV = Integer.parseInt(ID_Vuelo_Vue);
                    vuelta.setID(idV);
                    r.setVueloVuelta(vuelta);
                }
                return r;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    public boolean asientosAutomaticosIDA(Connection con, Reserva reserva) throws SQLException {
        try {
            con.setAutoCommit(false);
            /* obtener id pasajeros*/
            // recorrer todo el arraylist y sacando el id mandarlo la funcion
            ArrayList<Pasajero> pasa = obtenerPasajeros(con, reserva);
            //* tiene un arraylist con pasajeros y su id*/
            Iterator<Pasajero> itP = pasa.iterator();
            while (itP.hasNext()) {
                Pasajero p = itP.next();
                asignarAsiento(con, p.getID(), reserva.getVueloIda().getID());
            }
            /* actualizar la tabla reserva para ver facturacion */
            actualizarFacturacionIDA(con, reserva);
            con.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                    return false;
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                    throw new SQLException(ex1);
                }
            }
            return false;
        }
        throw new SQLException();
    }

    public boolean asientosAutomaticosVuelta(Connection con, Reserva reserva) throws SQLException {
        try {
            con.setAutoCommit(false);
            /* obtener id pasajeros*/
            // recorrer todo el arraylist y sacando el id mandarlo la funcion
            ArrayList<Pasajero> pasa = obtenerPasajerosV(con, reserva);
            //* tiene un arraylist con pasajeros y su id*/
            Iterator<Pasajero> itP = pasa.iterator();
            while (itP.hasNext()) {
                Pasajero p = itP.next();
                asignarAsiento(con, p.getID(), reserva.getVueloVuelta().getID());
            }
            /* actualizar la tabla reserva para ver facturacion */
            actualizarFacturacionVuelta(con, reserva);
            con.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                    return false;
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                    throw new SQLException(ex1);
                }
            }
            return false;
        }
        throw new SQLException();
    }

    private boolean actualizarFacturacionIDA(Connection con, Reserva reserva) throws SQLException {
        String sql = "update reserva set FacturarI = ? where COD_Reserva=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, "si");
            sentencia.setString(2, reserva.getCOD_Reserva());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean actualizarFacturacionVuelta(Connection con, Reserva reserva) throws SQLException {
        String sql = "update reserva set FacturarV = ? where COD_Reserva=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, "si");
            sentencia.setString(2, reserva.getCOD_Reserva());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean asignarAsiento(Connection con, int IDpasajeros, int IDVuelo) throws SQLException {
        String sql = "update ocupacion set IDPasajero = ? where (IDPasajero is null) and IDVuelo = ? LIMIT 1";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, IDpasajeros);
            sentencia.setInt(2, IDVuelo);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return true;
    }

    public Reserva obtenerPasajerosR(Connection con, Reserva r) {
        String sql = "select * from pasajero where ID_Reserva=(select ID from reserva where COD_Reserva=?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, r.getCOD_Reserva());

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String tratamiento = resultado.getString("Tratamiento");
                String nombre = resultado.getString("Nombre");
                String apellido = resultado.getString("Apellido");
                String tipoIde = resultado.getString("TipoIden");
                String numeroIden = resultado.getString("Identificador");
                String nacionalidad = resultado.getString("Nacionalidad");
                if (tratamiento != null) {
                    Pasajero p = new Pasajero(tratamiento, nombre, apellido, tipoIde, numeroIden, nacionalidad);
                    p.setID(id);
                    r.addPasajero(p);
                } else {
                    Pasajero p = new Pasajero(nombre, apellido, tipoIde, numeroIden, nacionalidad);
                    p.setID(id);
                    r.addPasajero(p);
                }

            }
            return r;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Vuelo obtenerVuelo(Connection con, int id) {
        String sql = "select * from vuelo where ID=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int ID = resultado.getInt("ID");
                int IDConexion = resultado.getInt("ID_Conexion");
                String CODVuelo = resultado.getString("COD_Vuelo");
                LocalDate fecha = resultado.getDate("Fecha").toLocalDate();
                Time Hora_Salida = resultado.getTime("Hora_Salida");
                Time Hora_Entrada = resultado.getTime("Hora_Entrada");
                double precio = resultado.getDouble("Precio");
                Time duracion = resultado.getTime("Duracion");
                int plazas = resultado.getInt("Plazas_Libres");
                Vuelo v = new Vuelo(IDConexion, CODVuelo, fecha, Hora_Salida, Hora_Entrada, precio, duracion, plazas);
                return v;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }

    public Reserva obtenerOcupaciones(Connection con, Reserva r) throws SQLException {
        try {
            con.setAutoCommit(false);
            Iterator<Pasajero> it = r.getPasajeros().iterator();
            while (it.hasNext()) {
                Pasajero p = it.next();
                if (!p.getTratamiento().equals("")) {
                    String ocupacion = ocupacionIndividual(con, p.getID());
                    p.setAsientoI(ocupacion);
                    Servicio prioridad = prioridadEmbarque(con, p.getID());
                    p.addServiciosIDA(prioridad);
                }
            }
            con.commit();
            return r;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }

    private String ocupacionIndividual(Connection con, int idPasajero) throws SQLException {
        String sql = "select * from ocupacion where IDPasajero = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idPasajero);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String Fila = resultado.getString("Fila");
                String Columna = resultado.getString("Columna");
                return Fila + " - " + Columna;
            }
        } catch (SQLException ex) {
            throw new SQLException("pue " + ex.getMessage());
        }
        throw new SQLException("pue ser aqui");
    }
    
    private Servicio prioridadEmbarque(Connection con, int idPasajero) throws SQLException {
        String sql = "select * from servicio where ID in (select ID_Servicio from pasajeroservicio where ID_Pasajero=? and ID_Servicio='5')";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idPasajero);

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String Servicio = resultado.getString("Servicio");
                double Precio = resultado.getDouble("Precio");
                Servicio s = new Servicio(Servicio, Precio);
                return s;
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException("pue " + ex.getMessage());
        }
    }

    public int registrarCliente(Connection con, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente values(null, ?, ?, ?, ?, ?, ?, ?, ?, AES_ENCRYPT(?, 'semilla'), ?)";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, cliente.getTratamiento());
            sentencia.setString(2, cliente.getNombre());
            sentencia.setString(3, cliente.getApellido());
            sentencia.setString(4, cliente.getTipoIden());
            sentencia.setString(5, cliente.getDNI());
            sentencia.setString(6, cliente.getDireccion());
            sentencia.setString(7, cliente.getCorreo());
            sentencia.setString(8, cliente.getUsuario());
            sentencia.setString(9, cliente.getPassword());
            sentencia.setString(10, cliente.getTelf());
            
            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
            
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    public Vuelo obtenerVueloDespegar(Connection con, Vuelo vuelo) throws SQLException {
        String sql = "select * from vuelo where COD_Vuelo = ? and Fecha = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, vuelo.getCODVuelo());
            sentencia.setString(2, vuelo.getFecha().toString());

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int ID = resultado.getInt("ID");
                int IDConexion = resultado.getInt("ID_Conexion");
                String CODVuelo = resultado.getString("COD_Vuelo");
                LocalDate fecha = resultado.getDate("Fecha").toLocalDate();
                Time Hora_Salida = resultado.getTime("Hora_Salida");
                Time Hora_Entrada = resultado.getTime("Hora_Entrada");
                double precio = resultado.getDouble("Precio");
                Time duracion = resultado.getTime("Duracion");
                int plazas = resultado.getInt("Plazas_Libres");
                int avion = resultado.getInt("Avion");
                Vuelo v = new Vuelo(IDConexion, CODVuelo, fecha, Hora_Salida, Hora_Entrada, precio, duracion, plazas);
                v.setID(ID);
                v.setAvion(avion);
                return v;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        throw new SQLException();
    }

    public ArrayList<Reserva> borrarVuelo(Connection con, Vuelo vuelo) throws SQLException {
        try {
            con.setAutoCommit(false);
            /* Obtenemos reservas */
            ArrayList<Reserva> reservas = ObtenerReservasD(con, vuelo);
            /* Obtenemos Pasajeros */
            Iterator<Reserva> it = reservas.iterator();
            while (it.hasNext()) {
                Reserva re = it.next();
                obtenerPasajerosDespegar(con, re);
                obtenerTarjetaDespegar(con, re);
            }
            /* Obtener servicios por cada pasajero */
            Iterator<Reserva> it2 = reservas.iterator();
            while (it2.hasNext()) {
                Reserva re = it2.next();
                Iterator<Pasajero> itP = re.getPasajeros().iterator();
                while (itP.hasNext()) {
                    Pasajero p = itP.next();
                    /* Obtengo los servicios */
                    obtenerServiciosD(con, p, vuelo);
                    /* Obtengo la ocupacion */
                    obtenerOcupacionD(con, p, vuelo);
                }
            }
            /* Introduciremos el vuelo en backup para obtener el ID */
            int idVueloBackup = insertarVuelo(con, vuelo);
            /* Comprobaremos si cada reserva si esta ya en las tablas*/
            Iterator<Reserva> it3 = reservas.iterator();
            while (it3.hasNext()) {
                Reserva re = it3.next();
                if (comprobarReserva(con, re)) {
                    /* Si el resultado es positiv es que ya estaba en las tablas
                      por lo tanto solo actualizaremos el id del vuelo que falte*/
                    insertarReservaYaDespegada(con, re, idVueloBackup);
                } else {
                    /* Si el resultado es negativo es porque antes no se introdujo
                       datos por lo tanto introducimos todo lo que tengamos */
                    // Aqui meto la tarjeta
                    insertarReservaDespegar(con, re, idVueloBackup);
                }
            }
            /* Eliminar las ocupaciones de ese vuelo y la eliminacion de los 
             * pasajeros servicios se harán en un trigger que se lanzara antes 
             * de borrar el vuelo.*/
            /* Pondremos a null en los id de los vuelos en las reservas donde esten ese vuelo */
            ponerNullIdVuelos(con, vuelo);
            /* Cuando eliminamos el vuelo lanzamos el trigger que elimina tambien:
             1. La ocupacion de ese vuelo.
             2. Los servicios que tenga contratado cada pasajero.
             3. Los pasajeros, primero los menores, sin tutor, y luego los adultos
                si es que hay que eliminarlos.
             4. La reserva en caso de que tenga que eliminarse.
            Tanto los pasajeros como la reserva se eliminen cuando los IDs de los vuelos
            estan a null, indicando que se han insertado esos datos en las tablas de backup.
            */
            eliminarVuelo(con, vuelo);
            con.commit();
            return reservas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                    throw new SQLException();
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                    throw new SQLException(ex1);
                }
            }
            throw new SQLException();
        }
    }

    private ArrayList<Reserva> ObtenerReservasD(Connection con, Vuelo vuelo) throws SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String sql = "select * from reserva where ID_Vuelo_Ida=? or ID_Vuelo_Vuelta=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, vuelo.getID());
            sentencia.setInt(2, vuelo.getID());

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int ID = resultado.getInt("ID");
                String COD_Reserva = resultado.getString("COD_Reserva");
                int ID_Cliente = resultado.getInt("ID_Cliente");
                int ID_Vuelo_Ida = resultado.getInt("ID_Vuelo_Ida");
                int ID_Vuelo_Vuelta = resultado.getInt("ID_Vuelo_Vuelta");
                LocalDate fecha = resultado.getDate("Fecha").toLocalDate();
                int ID_Tarjeta = resultado.getInt("ID_Tarjeta");
                double Coste = resultado.getDouble("Coste");
                String FacturarI = resultado.getString("FacturarI");
                String FacturarV = resultado.getString("FacturarV");
                Cliente c = new Cliente(ID_Cliente);
                if (ID_Vuelo_Ida == vuelo.getID()) {
                    ID_Vuelo_Vuelta = 0;
                }
                if (ID_Vuelo_Vuelta == vuelo.getID()) {
                    ID_Vuelo_Ida = 0;
                }
                Vuelo vIda = new Vuelo(ID_Vuelo_Ida);
                Vuelo vVuelta = new Vuelo(ID_Vuelo_Vuelta);
                Reserva r = new Reserva(COD_Reserva, c, vIda, vVuelta, fecha, ID_Tarjeta, Coste, FacturarI, FacturarV);
                r.setID(ID);
                reservas.add(r);
            }
            return reservas;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private boolean obtenerPasajerosDespegar(Connection con, Reserva reserva) throws SQLException {
        String sql = "select * from pasajero where ID_Reserva=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, reserva.getID());
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                int ID = resultado.getInt("ID");
                String Tratamiento = resultado.getString("Tratamiento");
                String Nombre = resultado.getString("Nombre");
                String Apellido = resultado.getString("Apellido");
                String TipoIden = resultado.getString("TipoIden");
                String Identificador = resultado.getString("Identificador");
                LocalDate FechaCad = resultado.getDate("FechaCad").toLocalDate();
                LocalDate Fecha_Nac = resultado.getDate("Fecha_Nac").toLocalDate();
                String Nacionalidad = resultado.getString("Nacionalidad");
                String Tutor = resultado.getString("Tutor");
                String ID_Tutor = resultado.getString("ID_Tutor");
                int ID_Reserva = resultado.getInt("ID_Reserva");

                Pasajero p = new Pasajero(Tratamiento, Nombre, Apellido, Identificador, TipoIden, FechaCad, Nacionalidad, Tutor, ID_Tutor, Fecha_Nac);
                p.setID(ID);
                p.setIDReserva(ID_Reserva);
                reserva.addPasajero(p);
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private boolean obtenerServiciosD(Connection con, Pasajero p, Vuelo vuelo) throws SQLException {
        String sql = "select * from servicio where id in (select ID_Servicio from pasajeroservicio where ID_Pasajero=? and ID_Vuelo=?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, p.getID());
            sentencia.setInt(2, vuelo.getID());

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                int ID = resultado.getInt("ID");
                String nombre = resultado.getString("Servicio");
                double precio = resultado.getDouble("Precio");
                Servicio s = new Servicio(nombre, precio);
                s.setID(ID);
                p.addServiciosIDA(s);
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private boolean obtenerOcupacionD(Connection con, Pasajero p, Vuelo vuelo) throws SQLException {
        String sql = "select * from ocupacion where IDPasajero=? and IDVuelo=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, p.getID());
            sentencia.setInt(2, vuelo.getID());

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String Fila = resultado.getString("Fila");
                String Columna = resultado.getString("Columna");
                p.setAsientoI(Fila + "-" + Columna);
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private boolean comprobarReserva(Connection con, Reserva reserva) throws SQLException {
        String sql = "select * from backupreserva where COD_Reserva=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, reserva.getCOD_Reserva());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private int insertarVuelo(Connection con, Vuelo vuelo) throws SQLException {
        String sql = "INSERT INTO backupvuelo values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            //Preparamos la sentencia para que nos pueda devolver el ID autoincremental.
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, vuelo.getIDConexion());
            sentencia.setString(2, vuelo.getCODVuelo());
            sentencia.setString(3, vuelo.getFecha().toString());
            sentencia.setTime(4, vuelo.getHora_Salida());
            sentencia.setTime(5, vuelo.getHora_Entrada());
            sentencia.setDouble(6, vuelo.getPrecio());
            sentencia.setTime(7, vuelo.getDuracion());
            sentencia.setInt(8, vuelo.getPlazas());
            sentencia.setInt(9, vuelo.getAvion());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private void insertarReservaDespegar(Connection con, Reserva reserva, int idVueloBackup) throws SQLException {
        /* Montar transeaccion para meter reservas */
        try {
            /* Comprobar la tarjeta */
            if(comprobarTarjeta(con, reserva)){
                // devolver el id
                int idTarjetaBackup = obtenerIDTarjetadeBackup(con, reserva);
                reserva.setIdTarjeta(idTarjetaBackup);
            }else{
                // meter la tarjeta y obtener el ID
                int idTarjetaBackup = introducirIDTarjetadeBackup(con, reserva);
                reserva.setIdTarjeta(idTarjetaBackup);
            }
            /* Introducimos reserva */
            int idReserva = IntroducirReservaD(con, reserva, idVueloBackup);
            /* Array de ids de pasajeros */
            arrayPosiciones idPasajeros = new arrayPosiciones();
            Iterator<Pasajero> it = reserva.getPasajeros().iterator();
            while (it.hasNext()) {
                Pasajero p = it.next();
                /* Introducimos pasajeros */
                int id;
                if (p.getID_tutor() == null) {
                    id = introducirPasajeroADes(con, p, idReserva);
                    int pop = p.getID();
                    idPasajeros.add(new Posicion(p.getID(), id));
                } else {
                    String r = p.getID_tutor();
                    int pos = Integer.parseInt(r);
                    int idTutor = idPasajeros.obtenerID(pos);
                    id = introducirPasajeroMDes(con, p, idReserva, idTutor);
                }
                if (p.getID_tutor() == null) {
                    /* ADULTO */
                    /* Introducimos servicios */
                    Iterator<Servicio> it3 = p.getServiciosIDA().iterator();
                    while (it3.hasNext()) {
                        Servicio s = it3.next();
                        introducirServicioD(con, s, id, idVueloBackup);
                    }
                    /* Introducimos ocupacion */
                    introducirOcupacionD(con, p, idVueloBackup, id);
                }
                if (p.getID_tutor() != null) {
                    if (p.getTratamiento() != null) {
                        /* NIÑO */
                        /* Introducimos servicios */
                        Iterator<Servicio> it3 = p.getServiciosIDA().iterator();
                        while (it3.hasNext()) {
                            Servicio s = it3.next();
                            introducirServicioD(con, s, id, idVueloBackup);
                        }
                        /* Introducimos ocupacion */
                        introducirOcupacionD(con, p, idVueloBackup, id);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }

    private int IntroducirReservaD(Connection con, Reserva reserva, int idVueloBackup) throws SQLException {
        String sql = "insert into backupreserva values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, reserva.getCOD_Reserva());
            sentencia.setInt(2, reserva.getCliente().getID());
            if (reserva.getVueloIda().getID() == 0) {
                sentencia.setString(3, null);
            } else {
                sentencia.setInt(3, idVueloBackup);
            }
            if (reserva.getVueloVuelta().getID() == 0) {
                sentencia.setString(4, null);
            } else {
                sentencia.setInt(4, idVueloBackup);
            }
            sentencia.setString(5, reserva.getFecha().toString());
            sentencia.setInt(6, reserva.getIdTarjeta());
            sentencia.setDouble(7, reserva.getaPagar());
            sentencia.setString(8, reserva.getFacturacionIda());
            sentencia.setString(9, reserva.getFacturacionVuelta());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private int introducirPasajeroADes(Connection con, Pasajero pasajero, int IDReserva) throws SQLException {
        String sql = "INSERT INTO backuppasajero values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, pasajero.getTratamiento());
            sentencia.setString(2, pasajero.getNombre());
            sentencia.setString(3, pasajero.getApellidos());
            sentencia.setString(4, pasajero.getTipoIden());
            sentencia.setString(5, pasajero.getIdentificador());
            sentencia.setString(6, pasajero.getFecha_caducidad().toString());
            sentencia.setString(7, pasajero.getFecha_nacimiento().toString());
            sentencia.setString(8, pasajero.getNacionalidad());
            sentencia.setString(9, pasajero.getTutorS());
            sentencia.setInt(10, IDReserva);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private int introducirPasajeroMDes(Connection con, Pasajero pasajero, int IDReserva, int idAdulto) throws SQLException {
        String sql = "INSERT INTO backuppasajero values(null, ?, ?, ?, ?, ?, ?, ?, ?, null, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, pasajero.getTratamiento());
            sentencia.setString(2, pasajero.getNombre());
            sentencia.setString(3, pasajero.getApellidos());
            sentencia.setString(4, pasajero.getTipoIden());
            sentencia.setString(5, pasajero.getIdentificador());
            sentencia.setString(6, pasajero.getFecha_caducidad().toString());
            sentencia.setString(7, pasajero.getFecha_nacimiento().toString());
            sentencia.setString(8, pasajero.getNacionalidad());
            sentencia.setInt(9, idAdulto);
            sentencia.setInt(10, IDReserva);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private boolean introducirServicioD(Connection con, Servicio servicio, int idPasajero, int vuelo) throws SQLException {
        try {
            int idServicio;
            /* Compruebo si existe ese servicio en la base de datos */
            if(comprobarBackupServicio(con, servicio)){
                /* Si es que si, obtengo el ID */
                idServicio = obtenerIDServicio(con, servicio);
            }else{
                /* Si es que no, introduzco y obtengo el ID */
                idServicio = introducirServicioBackup(con, servicio);
            }/* Introduzco en backuppasajeroservicio */
            introducirServicioBackup(con, idServicio, idPasajero, vuelo);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }
    
    private boolean comprobarBackupServicio(Connection con, Servicio servicio) throws SQLException{
        String sql = "select * from backupservicio where Servicio=? and Precio=? ";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, servicio.getServicio());
            sentencia.setDouble(2, servicio.getPrecio());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    private int obtenerIDServicio(Connection con, Servicio servicio) throws SQLException{
        String sql = "select * from backupservicio where Servicio=? and Precio=? ";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, servicio.getServicio());
            sentencia.setDouble(2, servicio.getPrecio());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                int ID = resultado.getInt("ID");
                return ID;
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    private int introducirServicioBackup(Connection con, Servicio servicio) throws SQLException{
        String sql = "insert into backupservicio values(null, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, servicio.getServicio());
            sentencia.setDouble(2, servicio.getPrecio());
            
            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }
            
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        throw new SQLException();
    }
    
    private boolean introducirServicioBackup(Connection con, int idServicio, int idPasajero, int vuelo) throws SQLException{
        String sql = "INSERT INTO backuppasajeroservicio values(null, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, idPasajero);
            sentencia.setInt(2, idServicio);
            sentencia.setInt(3, vuelo);

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    private boolean introducirOcupacionD(Connection con, Pasajero pasajero, int vuelo, int idPasajero) throws SQLException {
        String sql = "INSERT INTO backupocupacion values(null, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, idPasajero);
            sentencia.setInt(2, vuelo);
            sentencia.setString(3, "" + pasajero.getAsientoI().charAt(0));
            sentencia.setString(4, "" + pasajero.getAsientoI().charAt(2));

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    private boolean insertarReservaYADespegadaVuelta(Connection con, Reserva reserva, int idVueloBackup) throws SQLException {
        String sql = "update backupreserva set ID_Vuelo_Vuelta = ? where ID_Vuelo_Vuelta is null and COD_Reserva = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idVueloBackup);
            sentencia.setString(2, reserva.getCOD_Reserva());

            sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean insertarReservaYADespegadaIda(Connection con, Reserva reserva, int idVueloBackup) throws SQLException {
        String sql = "update backupreserva set ID_Vuelo_Ida = ? where ID_Vuelo_Ida is null and COD_Reserva = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idVueloBackup);
            sentencia.setString(2, reserva.getCOD_Reserva());

            sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        return true;
    }

    private int LeerPasajeroADes(Connection con, Pasajero pasajero, Reserva reserva) throws SQLException {
        String sql = "select * from backuppasajero where Identificador=? and ID_Reserva=(select ID from backupreserva where COD_Reserva = ?)";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, pasajero.getIdentificador());
            sentencia.setString(2, reserva.getCOD_Reserva());

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                int ID = resultado.getInt("ID");
                return ID;
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private void insertarReservaYaDespegada(Connection con, Reserva re, int idVueloBackup) throws SQLException {
        try {
            insertarReservaYADespegadaIda(con, re, idVueloBackup);
            insertarReservaYADespegadaVuelta(con, re, idVueloBackup);
            Iterator<Pasajero> it01 = re.getPasajeros().iterator();
            while (it01.hasNext()) {
                Pasajero p = it01.next();
                int id = LeerPasajeroADes(con, p, re);
                p.setID(id);
                if (p.getID_tutor() == null) {
                    /* ADULTO */
                    /* Introducimos servicios */
                    Iterator<Servicio> it3 = p.getServiciosIDA().iterator();
                    while (it3.hasNext()) {
                        Servicio s = it3.next();
                        introducirServicioD(con, s, p.getID(), idVueloBackup);
                    }
                    /* Introducimos ocupacion */
                    introducirOcupacionD(con, p, idVueloBackup, p.getID());
                }
                if (p.getID_tutor() != null) {
                    if (p.getTratamiento() != null) {
                        /* NIÑO */
                        /* Introducimos servicios */
                        Iterator<Servicio> it3 = p.getServiciosIDA().iterator();
                        while (it3.hasNext()) {
                            Servicio s = it3.next();
                            introducirServicioD(con, s, p.getID(), idVueloBackup);
                        }
                        /* Introducimos ocupacion */
                        introducirOcupacionD(con, p, idVueloBackup, p.getID());
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }

    private boolean ponerNullIdVuelos(Connection con, Vuelo vuelo) throws SQLException {
        try {
            ponerNullIdVuelosIda(con, vuelo);
            ponerNullIdVuelosVuelta(con, vuelo);
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private boolean ponerNullIdVuelosIda(Connection con, Vuelo vuelo) throws SQLException {
        String sql = "update reserva set ID_Vuelo_Ida = null where ID_Vuelo_Ida=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, vuelo.getID());

            sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean ponerNullIdVuelosVuelta(Connection con, Vuelo vuelo) throws SQLException {
        String sql = "update reserva set ID_Vuelo_Vuelta = null where ID_Vuelo_Vuelta=?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, vuelo.getID());

            sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
        return true;
    }

    private boolean eliminarVuelo(Connection con, Vuelo vuelo) throws SQLException {
        String sql = "delete from vuelo where COD_Vuelo = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, vuelo.getCODVuelo());

            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
    }

    private boolean comprobarTarjeta(Connection con, Reserva reserva) throws SQLException {
        String sql = "select * from backuptarjeta where backuptarjeta.Tipo = ? and backuptarjeta.CSV = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, reserva.getTarjeta().getTipo());
            sentencia.setString(2, reserva.getTarjeta().getCSV());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private int obtenerIDTarjetadeBackup(Connection con, Reserva reserva) throws SQLException {
        String sql = "select * from backuptarjeta where backuptarjeta.Tipo = ? and backuptarjeta.CSV = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, reserva.getTarjeta().getTipo());
            sentencia.setString(2, reserva.getTarjeta().getCSV());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                int ID = resultado.getInt("ID");
                return ID;
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private int introducirIDTarjetadeBackup(Connection con, Reserva reserva) throws SQLException {
        String sql = "insert into backuptarjeta values( null, ?, AES_ENCRYPT(?, 'semilla'), ?, ?, ?, ? )";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, reserva.getTarjeta().getID_Cliente());
            sentencia.setString(2, reserva.getTarjeta().getNumero());
            sentencia.setString(3, reserva.getTarjeta().getTipo());
            sentencia.setString(4, reserva.getTarjeta().getCSV());
            sentencia.setString(5, reserva.getTarjeta().getMesCaducidad());
            sentencia.setString(6, reserva.getTarjeta().getAñoCaducidad());

            if (sentencia.executeUpdate() != 1) {
                throw new SQLException(); //fila no insertada
            }

            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs != null && rs.next()) {
                long ID = rs.getLong(1);
                return (int) ID;
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        throw new SQLException();
    }

    private boolean obtenerTarjetaDespegar(Connection con, Reserva reserva) throws SQLException {
        String sql = "select ID, ID_Cliente, AES_DECRYPT(numero, 'semilla') as 'numero', Tipo, CSV, Mes_Caducidad, Ano_Caducidad from tarjeta where ID = ?";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, reserva.getIdTarjeta());

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                int ID = resultado.getInt("ID");
                int ID_Cliente = resultado.getInt("ID_Cliente");
                String Numero = resultado.getString("numero");
                String Tipo = resultado.getString("Tipo");
                String CSV = resultado.getString("CSV");
                String Mes_Caducidad = resultado.getString("Mes_Caducidad");
                String Ano_Caducidad = resultado.getString("Ano_Caducidad");

                Tarjeta t = new Tarjeta(ID_Cliente, Numero, Tipo, CSV, Mes_Caducidad, Ano_Caducidad);
                t.setID(ID);
                reserva.setTarjeta(t);
                return true;
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public Estadistica obtenerEstadisticas(Connection con, Estadistica estadistica) {
        String sql = "select (select Servicio from backupservicio where ID = ID_Servicio ) as 'nom', ID_Servicio, count(ID_Servicio)*(select Precio from backupServicio where ID = ID_Servicio) as 'tot' from backuppasajeroservicio where ID_Vuelo in (select ID from backupvuelo where ID_Conexion = (select ID from backupconexion where ID_Origen = ? and ID_Destino = ? and Fecha between ? and ? )) group by ID_Servicio order by tot desc";
        PreparedStatement sentencia;
        try {
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, estadistica.getIdOrigen());
            sentencia.setInt(2, estadistica.getIdDestino());
            sentencia.setString(3, estadistica.getFechaMIN().toString());//fecha min
            sentencia.setString(4, estadistica.getFechaMAX().toString());//fecha max
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID_Servicio");
                double totalServicios = resultado.getDouble("tot");
                String nombre = resultado.getString("nom");

                ServicioEstadistico servicios = new ServicioEstadistico();
                servicios.setID(id);
                servicios.setServicio(nombre);
                servicios.setTotalCantidadPrecio(totalServicios);
                estadistica.addServicioEstadistico(servicios);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return estadistica;
    }
}
