package com.edutecno.servlets;

import com.edutecno.dao.HoroscopoDAOImpl;
import com.edutecno.dao.UsuarioDAOImpl;
import com.edutecno.modelo.Horoscopo;
import com.edutecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    private UsuarioDAOImpl usuarioDAO;
    private HoroscopoDAOImpl horoscopoDAO;

    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
        horoscopoDAO = new HoroscopoDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Recoger parámetros del formulario
            String nombre = request.getParameter("nombre");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String fechaNacimientoStr = request.getParameter("fechaNacimiento");
            String password = request.getParameter("password");

            // Convertir fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);

            // Calcular animal del horóscopo chino
            String animal = calcularAnimalHoroscopo(fechaNacimiento);

            // Crear usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setPassword(password);
            usuario.setAnimal(animal);

            // Registrar usuario
            if (usuarioDAO.crearUsuario(usuario)) {
                // Registro exitoso
                response.sendRedirect("login.jsp");
            } else {
                // Error en registro
                request.setAttribute("error", "No se pudo registrar el usuario");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de fecha inválido");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

    private String calcularAnimalHoroscopo(Date fechaNacimiento) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(yearFormat.format(fechaNacimiento));

        List<Horoscopo> horoscopos = horoscopoDAO.obtenerHoroscopo();

        for (Horoscopo h : horoscopos) {
            if (year >= Integer.parseInt(yearFormat.format(h.getFecha_inicio())) &&
                    year <= Integer.parseInt(yearFormat.format(h.getFecha_fin()))) {
                return h.getAnimal();
            }
        }

        return "No encontrado";
    }
}

