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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ModificarUsuarioServlet") //ModificarUsuarioServlet
public class ModificarUsuarioServlet extends HttpServlet {
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

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);

                if (usuario != null) {
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("modificar-usuario.jsp").forward(request, response);
                } else {
                    response.sendRedirect("listarUsuarios.jsp");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("listarUsuarios.jsp");
            }
        } else {
            response.sendRedirect("listarUsuarios.jsp");
        }
    }


//        String username = (String) session.getAttribute("usuario");
//        Usuario usuario = usuarioDAO.buscarUsuarioPorUsername(username);
//        request.setAttribute("usuario", usuario);
//        request.getRequestDispatcher("modificar-usuario.jsp").forward(request, response);


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Recoger parámetros
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String fechaNacimientoStr = request.getParameter("fechaNacimiento");
            String password = request.getParameter("password");

            // Obtener username de la sesión
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("usuario");

            // Convertir fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);

            // Crear objeto usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setPassword(password);

            // Modificar usuario
            if (usuarioDAO.modificarUsuario(usuario)) {
                response.sendRedirect("consulta-horoscopo.jsp");
            } else {
                request.setAttribute("error", "No se pudo modificar el usuario");
                request.getRequestDispatcher("modificar-usuario.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de fecha inválido");
            request.getRequestDispatcher("modificar-usuario.jsp").forward(request, response);
        }
    }
}

