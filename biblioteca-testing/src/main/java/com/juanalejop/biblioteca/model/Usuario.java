package com.juanalejop.biblioteca.model;

import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private final String nombreUsuario;
    private final List<Prestamo> historialPrestamo;

    public Usuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser null o vacío");
        }
        this.nombreUsuario = nombreUsuario;
        this.historialPrestamo = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public List<Prestamo> getHistorialPrestamo() {
        return historialPrestamo;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null");
        }
        historialPrestamo.add(prestamo);
    }
}