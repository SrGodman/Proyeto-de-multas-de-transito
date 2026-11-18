package com.pnc.multas.dao;

import com.pnc.multas.modelo.DetalleInfraccion;
import com.pnc.multas.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetalleInfraccionDAO {

    private static final String SELECT_BASE =
            "SELECT di.*, m.numero_multa FROM detalle_infracciones di "
            + "JOIN multas m ON m.id = di.id_multa ";

    public DetalleInfraccion guardar(DetalleInfraccion d) {
        String sql = "INSERT INTO detalle_infracciones (id_multa, articulo_ley, descripcion, monto, estado) "
                + "VALUES (?,?,?,?,?)";
        Connection con = ConexionBD.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getIdMulta());
            ps.setString(2, d.getArticuloLey());
            ps.setString(3, d.getDescripcion());
            ps.setDouble(4, d.getMonto());
            ps.setString(5, d.getEstado());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    d.setCorrelativo(keys.getInt(1));
                }
            }
            return d;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el detalle de infracción: " + e.getMessage(), e);
        }
    }

    public void actualizarEstado(int correlativo, String estado) {
        String sql = "UPDATE detalle_infracciones SET estado = ? WHERE correlativo = ?";
        Connection con = ConexionBD.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, correlativo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el detalle de infracción: " + e.getMessage(), e);
        }
    }

    public List<DetalleInfraccion> listarPorMulta(int idMulta) {
        String sql = SELECT_BASE + "WHERE di.id_multa = ? ORDER BY di.correlativo";
        List<DetalleInfraccion> lista = new ArrayList<>();
        Connection con = ConexionBD.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMulta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar el detalle de infracciones: " + e.getMessage(), e);
        }
        return lista;
    }

    private DetalleInfraccion mapear(ResultSet rs) throws SQLException {
        DetalleInfraccion d = new DetalleInfraccion();
        d.setCorrelativo(rs.getInt("correlativo"));
        d.setIdMulta(rs.getInt("id_multa"));
        d.setNumeroMulta(rs.getString("numero_multa"));
        d.setArticuloLey(rs.getString("articulo_ley"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setMonto(rs.getDouble("monto"));
        d.setEstado(rs.getString("estado"));
        return d;
    }
}
