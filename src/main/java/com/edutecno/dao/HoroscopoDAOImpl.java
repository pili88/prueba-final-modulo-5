// Implementación HoroscopoDAOImpl
package com.edutecno.dao;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoroscopoDAOImpl implements HoroscopoDAO {
    private Conexion conexion;

    public HoroscopoDAOImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> horoscopos = new ArrayList<>();
        String sql = "SELECT * FROM HOROSCOPO";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Horoscopo horoscopo = new Horoscopo(
                        rs.getString("ANIMAL"),
                        rs.getDate("FECHA_INICIO"),
                        rs.getDate("FECHA_FIN")
                );
                horoscopos.add(horoscopo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return horoscopos;
    }

    @Override
    public Horoscopo buscarHoroscopoPorAnimal(String animal) {
        return null;
    }
}
