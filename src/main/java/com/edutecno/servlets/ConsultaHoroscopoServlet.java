package com.edutecno.servlets;

import com.edutecno.dao.HoroscopoDAOImpl;
import com.edutecno.modelo.Horoscopo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/consulta-horoscopo")
public class ConsultaHoroscopoServlet extends HttpServlet {
    private HoroscopoDAOImpl horoscopoDAO;

    public void init() {
        horoscopoDAO = new HoroscopoDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener el parámetro 'animal' si lo hay
        String animal = request.getParameter("animal");
        if (animal != null && !animal.isEmpty()) {
            Horoscopo horoscopo = horoscopoDAO.buscarHoroscopoPorAnimal(animal);
            request.setAttribute("horoscopo", horoscopo);
        }

        // Redirigir a la página JSP
        request.getRequestDispatcher("consulta-horoscopo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener fecha de nacimiento del formulario
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        // Lógica para calcular el animal del horóscopo chino basado en la fecha
        if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
            String animal = calcularAnimalHoroscopo(fechaNacimiento);  // Implementa esta función
            request.setAttribute("animal", animal);
        }

        // Redirigir al JSP para mostrar el resultado
        request.getRequestDispatcher("consulta-horoscopo.jsp").forward(request, response);
    }

    // Metodo para calcular el animal del horóscopo chino
    private String calcularAnimalHoroscopo(String fechaNacimiento) {
        // Implementa la lógica para calcular el animal basado en la fecha
        // Por ejemplo, puedes utilizar el año de nacimiento para determinar el animal
        int anioNacimiento = Integer.parseInt(fechaNacimiento.split("-")[0]);  // Suponiendo formato yyyy-mm-dd

        // Tabla de animales del horóscopo chino
        String[] animales = {
                "Rata", "Buey", "Tigre", "Conejo", "Dragón", "Serpiente", "Caballo", "Cabra", "Mono", "Gallo", "Perro", "Cerdo"
        };

        // Calcular el signo chino basado en el año (simplemente un ejemplo)
        int indice = (anioNacimiento - 1900) % 12; // Ajuste para el ciclo de 12 años
        return animales[indice];
    }
}


