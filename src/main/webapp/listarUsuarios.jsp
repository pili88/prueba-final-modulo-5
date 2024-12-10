<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.edutecno.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .usuarios-container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            padding: 30px;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="usuarios-container">
                <h2 class="text-center mb-4">Listado de Usuarios Registrados</h2>

                <%
                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                    String mensaje = (String) request.getAttribute("mensaje");
                    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
                %>

                <% if (mensaje != null) { %>
                <div class="alert <%= tipoMensaje != null ? tipoMensaje : "alert-info" %>" role="alert">
                    <%= mensaje %>
                </div>
                <% } %>

                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Fecha Nacimiento</th>
                            <th>Animal Horóscopo</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            if (usuarios != null && !usuarios.isEmpty()) {
                                for (Usuario usuario : usuarios) {
                        %>
                        <tr>
                            <td><%= usuario.getId() %></td>
                            <td><%= usuario.getNombre() %></td>
                            <td><%= usuario.getUsername() %></td>
                            <td><%= usuario.getEmail() %></td>
                            <td><%= usuario.getFechaNacimiento() %></td>
                            <td><%= usuario.getAnimal() != null ? usuario.getAnimal() : "No asignado" %></td>
                            <td>
                                <div class="btn-group" role="group">
                                    <form action="//modificar-usuario" method="get" class="d-inline me-1">
                                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                        <button type="submit" class="btn btn-sm btn-primary">
                                            <i class="bi bi-pencil"></i> Editar
                                        </button>
                                    </form>
                                    <form action="//eliminar-usuario" method="post" class="d-inline">
                                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                        <button type="submit" class="btn btn-sm btn-danger"
                                                onclick="return confirm('¿Estás seguro de eliminar este usuario?')">
                                            <i class="bi bi-trash"></i> Eliminar
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center">No hay usuarios registrados</td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>

                <div class="text-center mt-3">
                    <a href="registro.jsp" class="btn btn-success me-2">
                        <i class="bi bi-person-plus"></i> Agregar Nuevo Usuario
                    </a>
                    <a href="consulta-horoscopo.jsp" class="btn btn-info">
                        <i class="bi bi-search"></i> Consultar Horóscopo
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

