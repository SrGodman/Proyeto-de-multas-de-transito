package com.pnc.multas.dao;

import com.pnc.multas.modelo.Multa;
import com.pnc.multas.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos para la entidad Multa.
 *
 * El número de multa se genera de forma automática con el formato
 * correlativo-año (ej. 1-2022, 2-2022, ... 1-2023, 2-2023), reiniciando
 * el correlativo a 1 cada vez que inicia un año nuevo.
 */
public class MultaDAO {

    private static final DateTimeFormatter FMT_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Columnas comunes a todos los SELECT, con el join a catálogos para mostrar nombres. */
    private static final String SELECT_BASE =
            "SELECT m.*, d.nombre AS nombre_departamento, mu.nombre AS nombre_municipio "
            + "FROM multas m "
            + "JOIN departamentos d ON d.id = m.id_departamento "
            + "JOIN municipios mu ON mu.id = m.id_municipio ";

    /**
     * Inserta una nueva multa. La regla de negocio RN1 (al menos placa, dpi
     * o licencia) debe validarse ANTES de llamar a este método, típicamente
     * en la capa de vista, para poder mostrar el mensaje de alerta al usuario.
     */
    public Multa guardar(Multa m) {
        Connection con = ConexionBD.getConexion();
        int anioActual = LocalDateTime.now().getYear();
        try {
            con.setAutoCommit(false);

            int siguienteCorrelativo = obtenerSiguienteCorrelativo(con, anioActual);
            String numeroMulta = siguienteCorrelativo + "-" + anioActual;
            String fechaHora = LocalDateTime.now().format(FMT_FECHA);

            String sql = "INSERT INTO multas (numero_multa, anio, correlativo_anio, fecha_hora, agente, "
                    + "id_departamento, id_municipio, direccion_exacta, dpi_conductor, licencia_conductor, "
                    + "nombre_conductor, placa_vehiculo, marca_vehiculo, linea_vehiculo, color_vehiculo, "
                    + "estado, justificacion_anulado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, numeroMulta);
                ps.setInt(2, anioActual);
                ps.setInt(3, siguienteCorrelativo);
                ps.setString(4, fechaHora);
                ps.setString(5, m.getAgente());
                ps.setInt(6, m.getIdDepartamento());
                ps.setInt(7, m.getIdMunicipio());
                ps.setString(8, m.getDireccionExacta());
                ps.setString(9, blankToNull(m.getDpiConductor()));
                ps.setString(10, blankToNull(m.getLicenciaConductor()));
                ps.setString(11, blankToNull(m.getNombreConductor()));
                ps.setString(12, blankToNull(m.getPlacaVehiculo()));
                ps.setString(13, blankToNull(m.getMarcaVehiculo()));
                ps.setString(14, blankToNull(m.getLineaVehiculo()));
                ps.setString(15, blankToNull(m.getColorVehiculo()));
                ps.setString(16, m.getEstado());
                ps.setString(17, blankToNull(m.getJustificacionAnulado()));
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        m.setId(keys.getInt(1));
                    }
                }
            }
            con.commit();
            m.setNumeroMulta(numeroMulta);
            m.setFechaHora(fechaHora);
            return m;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            throw new RuntimeException("Error al guardar la multa: " + e.getMessage(), e);
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }

    /** Actualiza el estado (activa/anulada/pagada) y la justificación de anulado de una multa existente. */
    public void actualizarEstado(int idMulta, String estado, String justificacion) {
        String sql = "UPDATE multas SET estado = ?, justificacion_anulado = ? WHERE id = ?";
        Connection con = ConexionBD.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setString(2, blankToNull(justificacion));
            ps.setInt(3, idMulta);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado de la multa: " + e.getMessage(), e);
        }
    }

    private int obtenerSiguienteCorrelativo(Connection con, int anio) throws SQLException {
        String sql = "SELECT COALESCE(MAX(correlativo_anio), 0) AS maximo FROM multas WHERE anio = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, anio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maximo") + 1;
                }
            }
        }
        return 1;
    }

    public List<Multa> listarTodas() {
        String sql = SELECT_BASE + "ORDER BY m.id DESC";
        return ejecutarConsulta(sql, ps -> {
        });
    }

    /** Busca una multa por su número exacto (ej. "1-2022"). */
    public Multa buscarPorNumero(String numeroMulta) {
        String sql = SELECT_BASE + "WHERE m.numero_multa = ?";
        List<Multa> lista = ejecutarConsulta(sql, ps -> ps.setString(1, numeroMulta));
        return lista.isEmpty() ? null : lista.get(0);
    }

    /**
     * Búsqueda flexible por número de multa, placa, dpi o número de licencia
     * (coincidencia parcial, sin distinguir mayúsculas/minúsculas).
     */
    public List<Multa> buscar(String criterio) {
        String like = "%" + criterio.trim().toUpperCase() + "%";
        String sql = SELECT_BASE + "WHERE UPPER(m.numero_multa) LIKE ? "
                + "OR UPPER(m.placa_vehiculo) LIKE ? "
                + "OR UPPER(m.dpi_conductor) LIKE ? "
                + "OR UPPER(m.licencia_conductor) LIKE ? "
                + "ORDER BY m.id DESC";
        return ejecutarConsulta(sql, ps -> {
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
        });
    }

    /** Reporte por día exacto (yyyy-MM-dd), mes (yyyy-MM), año (yyyy) o rango de fechas. */
    public List<Multa> reportePorFecha(String desde, String hasta, String estadoFiltro) {
        StringBuilder sql = new StringBuilder(SELECT_BASE);
        // fecha_hora se guarda como texto 'yyyy-MM-dd HH:mm:ss'; comparar los primeros
        // 10 caracteres (yyyy-MM-dd) es válido tanto en SQLite como en PostgreSQL.
        sql.append("WHERE substring(m.fecha_hora, 1, 10) BETWEEN ? AND ? ");
        List<Object> params = new ArrayList<>();
        params.add(desde);
        params.add(hasta);
        if (estadoFiltro != null && !estadoFiltro.equals("TODAS")) {
            sql.append("AND m.estado = ? ");
            params.add(estadoFiltro);
        }
        sql.append("ORDER BY m.fecha_hora DESC");
        return ejecutarConsultaParametrizada(sql.toString(), params);
    }

    /** Reporte por departamento y/o municipio, con filtro opcional de estado. */
    public List<Multa> reportePorUbicacion(Integer idDepartamento, Integer idMunicipio, String estadoFiltro) {
        StringBuilder sql = new StringBuilder(SELECT_BASE);
        sql.append("WHERE 1 = 1 ");
        List<Object> params = new ArrayList<>();
        if (idDepartamento != null) {
            sql.append("AND m.id_departamento = ? ");
            params.add(idDepartamento);
        }
        if (idMunicipio != null) {
            sql.append("AND m.id_municipio = ? ");
            params.add(idMunicipio);
        }
        if (estadoFiltro != null && !estadoFiltro.equals("TODAS")) {
            sql.append("AND m.estado = ? ");
            params.add(estadoFiltro);
        }
        sql.append("ORDER BY m.fecha_hora DESC");
        return ejecutarConsultaParametrizada(sql.toString(), params);
    }

    /** Historial de multas impuestas por un agente específico (coincidencia parcial). */
    public List<Multa> historialPorAgente(String agente) {
        String sql = SELECT_BASE + "WHERE UPPER(m.agente) LIKE ? ORDER BY m.fecha_hora DESC";
        return ejecutarConsulta(sql, ps -> ps.setString(1, "%" + agente.trim().toUpperCase() + "%"));
    }

    // ---------------------------------------------------------------
    // Utilidades internas
    // ---------------------------------------------------------------

    private interface ParamSetter {
        void set(PreparedStatement ps) throws SQLException;
    }

    private List<Multa> ejecutarConsulta(String sql, ParamSetter setter) {
        Connection con = ConexionBD.getConexion();
        List<Multa> lista = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.set(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar multas: " + e.getMessage(), e);
        }
        return lista;
    }

    private List<Multa> ejecutarConsultaParametrizada(String sql, List<Object> params) {
        Connection con = ConexionBD.getConexion();
        List<Multa> lista = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar multas: " + e.getMessage(), e);
        }
        return lista;
    }

    private Multa mapear(ResultSet rs) throws SQLException {
        Multa m = new Multa();
        m.setId(rs.getInt("id"));
        m.setNumeroMulta(rs.getString("numero_multa"));
        m.setFechaHora(rs.getString("fecha_hora"));
        m.setAgente(rs.getString("agente"));
        m.setIdDepartamento(rs.getInt("id_departamento"));
        m.setNombreDepartamento(rs.getString("nombre_departamento"));
        m.setIdMunicipio(rs.getInt("id_municipio"));
        m.setNombreMunicipio(rs.getString("nombre_municipio"));
        m.setDireccionExacta(rs.getString("direccion_exacta"));
        m.setDpiConductor(rs.getString("dpi_conductor"));
        m.setLicenciaConductor(rs.getString("licencia_conductor"));
        m.setNombreConductor(rs.getString("nombre_conductor"));
        m.setPlacaVehiculo(rs.getString("placa_vehiculo"));
        m.setMarcaVehiculo(rs.getString("marca_vehiculo"));
        m.setLineaVehiculo(rs.getString("linea_vehiculo"));
        m.setColorVehiculo(rs.getString("color_vehiculo"));
        m.setEstado(rs.getString("estado"));
        m.setJustificacionAnulado(rs.getString("justificacion_anulado"));
        return m;
    }

    private String blankToNull(String s) {
        return (s == null || s.trim().isEmpty()) ? null : s.trim();
    }
}
