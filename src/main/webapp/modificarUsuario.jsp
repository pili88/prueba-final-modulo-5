<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.edutecno.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modificar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .modificar-container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            padding: 30px;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="modificar-container">
                <h2 class="text-center mb-4">Modificar Datos de Usuario</h2>

                <%
                    Usuario usuario = (Usuario) request.getAttribute("usuario");
                    if (usuario == null) {
                        usuario = new Usuario();
                    }
                %>

                <form action="//modificar-usuario" method="post">
                    <input type="hidden" name="id" value="<%= usuario.getId() %>">

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre Completo</label>
                            <input type="text" class="form-control" id="nombre" name="nombre"
                                   value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="username" class="form-label">Nombre de Usuario</label>
                            <input type="text" class="form-control" id="username" name="username"
                                   value="<%= usuario.getUsername() != null ? usuario.getUsername() : "" %>" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Correo Electrónico</label>
                        <input type="email" class="form-control" id="email" name="email"
                               value="<%= usuario.getEmail() != null ? usuario.getEmail() : "" %>" required>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento"
                                   value="<%= usuario.getFechaNacimiento() != null ? usuario.getFechaNacimiento().toString() : "" %>" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label">Nueva Contraseña (opcional)</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>

                    <%
                        String mensaje = (String) request.getAttribute("mensaje");
                        String tipoMensaje = (String) request.getAttribute("tipoMensaje");
                        if (mensaje != null) {
                    %>
                    <div class="alert <%= tipoMensaje != null ? tipoMensaje : "alert-info" %>" role="alert">
                        <%= mensaje %>
                    </div>
                    <% } %>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Actualizar Datos</button>
                    </div>
                </form>

                <div class="text-center mt-3">
                    <a href="listarUsuarios.jsp" class="btn btn-secondary">Volver a Lista de Usuarios</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

