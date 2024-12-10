<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.edutecno.modelo.Horoscopo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Horóscopo Chino</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3 class="text-center mb-0">Consulta de Horóscopo Chino</h3>
                </div>
                <div class="card-body">
                    <form action="consulta-horoscopo" method="post">
                        <div class="mb-3">
                            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Consultar Horóscopo</button>
                    </form>

                    <%
                        String animal = (String) request.getAttribute("animal");
                        if (animal != null) {
                    %>
                    <div class="alert alert-success mt-3 text-center" role="alert">
                        <h4>Tu animal del horóscopo chino es: <%= animal %></h4>
                    </div>
                    <% } %>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-header bg-info text-white">
                    <h4 class="text-center mb-0">Tabla de Animales del Horóscopo Chino</h4>
                </div>
                <div class="card-body">
                    <%
                        List<Horoscopo> horoscopos = (List<Horoscopo>) request.getAttribute("horoscopos");
                        if (horoscopos != null) {
                    %>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Animal</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (Horoscopo h : horoscopos) { %>
                        <tr>
                            <td><%= h.getAnimal() %></td>
                            <td><%= h.getFecha_inicio() %></td>
                            <td><%= h.getFecha_fin() %></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
