// Interface UsuarioDAO
package com.edutecno.dao;

import com.edutecno.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {
    boolean crearUsuario(Usuario usuario);
    Usuario buscarUsuarioPorUsername(String username);
    boolean modificarUsuario(Usuario usuario);
    boolean eliminarUsuario(String username);
    List<Usuario> listarUsuarios();
    boolean validarLogin(String username, String password);
}
