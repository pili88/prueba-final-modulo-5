package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAOImpl;
import com.edutecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {
    private UsuarioDAOImpl usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Obtener lista de usuarios
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();

            // Establecer atributos para la vista
            request.setAttribute("usuarios", usuarios);

            // Opcional: manejo de mensajes
            if (usuarios.isEmpty()) {
                request.setAttribute("mensaje", "No hay usuarios registrados");
                request.setAttribute("tipoMensaje", "alert-info");
            }

            // Enviar a la página de listado
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);

        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("mensaje", "Error al listar usuarios: " + e.getMessage());
            request.setAttribute("tipoMensaje", "alert-danger");
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        }
    }
}
