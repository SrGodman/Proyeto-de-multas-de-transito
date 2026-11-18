package com.pnc.multas.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Maneja la conexión única a la base de datos PostgreSQL y se encarga de
 * crear las tablas y el catálogo de departamentos/municipios la primera
 * vez que se ejecuta la aplicación.
 *
 * Los datos de conexión (host, puerto, nombre de la base, usuario y
 * clave) se leen del archivo db.properties (empaquetado junto a esta
 * clase). Edite ese archivo según su servidor antes de ejecutar.
 *
 * IMPORTANTE: a diferencia de SQLite, PostgreSQL requiere que la base de
 * datos ya exista en el servidor (el driver JDBC no puede crearla por
 * usted). Antes de ejecutar la aplicación por primera vez, cree la base
 * de datos, por ejemplo:
 *
 *     CREATE DATABASE multas_transito;
 *
 * Las tablas y el catálogo de departamentos/municipios sí se crean y
 * siembran automáticamente dentro de esa base de datos.
 */
public class ConexionBD {

    private static Connection conexion;
    private static Properties config;

    private ConexionBD() {
    }

    public static synchronized Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                cargarConfiguracion();
                Class.forName("org.postgresql.Driver");

                String host = config.getProperty("db.host", "localhost");
                String puerto = config.getProperty("db.port", "5432");
                String nombreBD = config.getProperty("db.nombre", "multas_transito");
                String usuario = config.getProperty("db.usuario", "postgres");
                String clave = config.getProperty("db.clave", "postgres");

                String url = "jdbc:postgresql://" + host + ":" + puerto + "/" + nombreBD;

                try {
                    conexion = DriverManager.getConnection(url, usuario, clave);
                } catch (SQLException e) {
                    throw new RuntimeException("No fue posible conectar a PostgreSQL en " + url + ".\n"
                            + "Verifique que el servidor esté encendido, que la base de datos '" + nombreBD
                            + "' ya exista (CREATE DATABASE " + nombreBD + ";) y que el usuario/clave "
                            + "configurados en db.properties sean correctos.\nDetalle: " + e.getMessage(), e);
                }

                inicializarEsquema();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver JDBC de PostgreSQL en el classpath: "
                    + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar la base de datos: " + e.getMessage(), e);
        }
        return conexion;
    }

    private static void cargarConfiguracion() {
        if (config != null) {
            return;
        }
        config = new Properties();
        try (InputStream is = ConexionBD.class.getResourceAsStream("db.properties")) {
            if (is != null) {
                config.load(is);
            } else {
                System.err.println("Aviso: no se encontró db.properties; se usarán valores por defecto "
                        + "(localhost:5432/multas_transito, usuario postgres).");
            }
        } catch (IOException e) {
            System.err.println("Aviso: no fue posible leer db.properties (" + e.getMessage()
                    + "); se usarán valores por defecto.");
        }
    }

    /**
     * Crea las tablas si no existen y siembra los catálogos de
     * departamentos/municipios de Guatemala la primera vez.
     */
    private static void inicializarEsquema() throws SQLException {
        try (Statement st = conexion.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS departamentos ("
                    + "id INTEGER PRIMARY KEY, "
                    + "nombre TEXT NOT NULL UNIQUE)");

            st.execute("CREATE TABLE IF NOT EXISTS municipios ("
                    + "id INTEGER PRIMARY KEY, "
                    + "id_departamento INTEGER NOT NULL, "
                    + "nombre TEXT NOT NULL, "
                    + "CONSTRAINT fk_municipio_departamento FOREIGN KEY (id_departamento) "
                    + "REFERENCES departamentos(id))");

            st.execute("CREATE TABLE IF NOT EXISTS multas ("
                    + "id SERIAL PRIMARY KEY, "
                    + "numero_multa TEXT NOT NULL UNIQUE, "
                    + "anio INTEGER NOT NULL, "
                    + "correlativo_anio INTEGER NOT NULL, "
                    + "fecha_hora TEXT NOT NULL, "
                    + "agente TEXT NOT NULL, "
                    + "id_departamento INTEGER NOT NULL, "
                    + "id_municipio INTEGER NOT NULL, "
                    + "direccion_exacta TEXT NOT NULL, "
                    + "dpi_conductor TEXT, "
                    + "licencia_conductor TEXT, "
                    + "nombre_conductor TEXT, "
                    + "placa_vehiculo TEXT, "
                    + "marca_vehiculo TEXT, "
                    + "linea_vehiculo TEXT, "
                    + "color_vehiculo TEXT, "
                    + "estado TEXT NOT NULL DEFAULT 'ACTIVA', "
                    + "justificacion_anulado TEXT, "
                    + "CONSTRAINT fk_multa_departamento FOREIGN KEY (id_departamento) REFERENCES departamentos(id), "
                    + "CONSTRAINT fk_multa_municipio FOREIGN KEY (id_municipio) REFERENCES municipios(id))");

            st.execute("CREATE TABLE IF NOT EXISTS detalle_infracciones ("
                    + "correlativo SERIAL PRIMARY KEY, "
                    + "id_multa INTEGER NOT NULL, "
                    + "articulo_ley TEXT NOT NULL, "
                    + "descripcion TEXT NOT NULL, "
                    + "monto NUMERIC(10,2) NOT NULL, "
                    + "estado TEXT NOT NULL DEFAULT 'ACTIVO', "
                    + "CONSTRAINT fk_detalle_multa FOREIGN KEY (id_multa) REFERENCES multas(id))");

            // Sembrar catálogo de departamentos/municipios solo la primera vez
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM departamentos")) {
                if (rs.next() && rs.getInt("total") == 0) {
                    ejecutarScriptSemilla();
                }
            }
        }
    }

    /**
     * Lee el archivo seed_data.sql empaquetado como recurso y ejecuta
     * cada sentencia INSERT para poblar departamentos y municipios.
     */
    private static void ejecutarScriptSemilla() {
        try (InputStream is = ConexionBD.class.getResourceAsStream("seed_data.sql")) {
            if (is == null) {
                System.err.println("Aviso: no se encontró seed_data.sql; el catálogo de "
                        + "departamentos/municipios quedará vacío.");
                return;
            }
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append('\n');
                }
            }
            try (Statement st = conexion.createStatement()) {
                conexion.setAutoCommit(false);
                for (String sentencia : contenido.toString().split(";")) {
                    String s = sentencia.trim();
                    if (!s.isEmpty()) {
                        st.execute(s);
                    }
                }
                conexion.commit();
                conexion.setAutoCommit(true);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el catálogo de departamentos/municipios: "
                    + e.getMessage(), e);
        }
    }

    public static void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            // se ignora al cerrar
        }
    }
}
