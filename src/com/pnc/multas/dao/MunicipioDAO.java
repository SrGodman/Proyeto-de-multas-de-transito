package com.pnc.multas.dao;

import com.pnc.multas.modelo.Municipio;
import com.pnc.multas.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {

    public List<Municipio> listarPorDepartamento(int idDepartamento) {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT id, id_departamento, nombre FROM municipios "
                + "WHERE id_departamento = ? ORDER BY nombre";
        Connection con = ConexionBD.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDepartamento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Municipio(rs.getInt("id"), rs.getInt("id_departamento"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar municipios: " + e.getMessage(), e);
        }
        return lista;
    }
}
