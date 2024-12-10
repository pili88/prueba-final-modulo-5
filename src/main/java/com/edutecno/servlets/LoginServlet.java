package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAOImpl usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usuarioDAO.validarLogin(username, password)) {
            // Crear sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", username);

            // Redirigir a página principal
            response.sendRedirect("consulta-horoscopo.jsp");
        } else {
            // Credenciales inválidas
            request.setAttribute("error", "Credenciales incorrectas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cerrar sesión
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}

