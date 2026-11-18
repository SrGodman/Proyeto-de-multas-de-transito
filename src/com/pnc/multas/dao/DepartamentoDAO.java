package com.pnc.multas.dao;

import com.pnc.multas.modelo.Departamento;
import com.pnc.multas.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public List<Departamento> listarTodos() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM departamentos ORDER BY nombre";
        Connection con = ConexionBD.getConexion();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Departamento(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar departamentos: " + e.getMessage(), e);
        }
        return lista;
    }
}
