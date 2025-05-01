package com.juanalejop.biblioteca.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa un usuario del sistema de biblioteca.
 * Almacena su nombre y un historial de préstamos realizados.
 */
public class Usuario {
    private final String nombreUsuario;
    private final List<Prestamo> historialPrestamo;

    /**
     * Crea un nuevo usuario con el nombre especificado.
     *
     * @param nombreUsuario el nombre del usuario (no puede ser null ni vacío)
     * @throws IllegalArgumentException si el nombre es null o solo espacios
     */
    public Usuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser null o vacío");
        }
        this.nombreUsuario = nombreUsuario;
        this.historialPrestamo = new ArrayList<>();
    }

    /**
     * @return el nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @return la lista de préstamos realizados por el usuario
     */
    public List<Prestamo> getHistorialPrestamo() {
        return historialPrestamo;
    }

    /**
     * Agrega un nuevo préstamo al historial del usuario.
     *
     * @param prestamo el préstamo a agregar (no puede ser null)
     * @throws IllegalArgumentException si el préstamo es null
     */
    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null");
        }
        historialPrestamo.add(prestamo);
    }
}