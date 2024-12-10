package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/eliminar-usuario")
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

