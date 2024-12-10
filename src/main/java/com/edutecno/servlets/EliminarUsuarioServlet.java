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

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
    private UsuarioDAOImpl usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Usuario usuarioAEliminar = usuarioDAO.buscarUsuarioPorId(id);

                if (usuarioAEliminar != null) {
                    if (usuarioDAO.eliminarUsuario(usuarioAEliminar.getUsername())) {
                        response.sendRedirect("ListarUsuariosServlet");
                    } else {
                        request.setAttribute("mensaje", "No se pudo eliminar el usuario");
                        request.setAttribute("tipoMensaje", "alert-danger");
                        request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
                    }
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("listarUsuarios.jsp");

                String username = (String) session.getAttribute("usuario");

                if (usuarioDAO.eliminarUsuario(username)) {
                    // Cerrar sesión después de eliminar
                    session.invalidate();
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("error", "No se pudo eliminar el usuario");
                    request.getRequestDispatcher("consulta-horoscopo.jsp").forward(request, response);
    }
}

        String username = (String) session.getAttribute("usuario");

        if (usuarioDAO.eliminarUsuario(username)) {
            // Cerrar sesión después de eliminar
            session.invalidate();
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "No se pudo eliminar el usuario");
            request.getRequestDispatcher("consulta-horoscopo.jsp").forward(request, response);

        }
        }
    }
}


