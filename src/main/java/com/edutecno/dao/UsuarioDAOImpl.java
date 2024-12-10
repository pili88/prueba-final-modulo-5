
// Implementación UsuarioDAOImpl
package com.edutecno.dao;

import com.edutecno.modelo.Usuario;
import com.edutecno.procesaconexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Conexion conexion;

    public UsuarioDAOImpl() {
        this.conexion = new Conexion();
    }
    // Metodo para buscar usuario por ID
    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM USUARIOS WHERE ID = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("ID"));
                    usuario.setNombre(rs.getString("NOMBRE"));
                    usuario.setUsername(rs.getString("USERNAME"));
                    usuario.setEmail(rs.getString("EMAIL"));
                    usuario.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                    usuario.setPassword(rs.getString("PASSWORD"));
                    usuario.setAnimal(rs.getString("ANIMAL"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (NOMBRE, USERNAME, EMAIL, FECHA_NACIMIENTO, PASSWORD, ANIMAL) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getUsername());
            pstm.setString(3, usuario.getEmail());
            pstm.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pstm.setString(5, usuario.getPassword());
            pstm.setString(6, usuario.getAnimal());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario buscarUsuarioPorUsername(String username) {
        String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, username);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("ID"));
                    usuario.setNombre(rs.getString("NOMBRE"));
                    usuario.setUsername(rs.getString("USERNAME"));
                    usuario.setEmail(rs.getString("EMAIL"));
                    usuario.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                    usuario.setPassword(rs.getString("PASSWORD"));
                    usuario.setAnimal(rs.getString("ANIMAL"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET NOMBRE = ?, EMAIL = ?, FECHA_NACIMIENTO = ?, PASSWORD = ?, ANIMAL = ? WHERE USERNAME = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getEmail());
            pstm.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pstm.setString(4, usuario.getPassword());
            pstm.setString(5, usuario.getAnimal());
            pstm.setString(6, usuario.getUsername());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String username) {
        String sql = "DELETE FROM USUARIOS WHERE USERNAME = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, username);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setAnimal(rs.getString("ANIMAL"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public boolean validarLogin(String username, String password) {
        String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, username);
            pstm.setString(2, password);

            try (ResultSet rs = pstm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
