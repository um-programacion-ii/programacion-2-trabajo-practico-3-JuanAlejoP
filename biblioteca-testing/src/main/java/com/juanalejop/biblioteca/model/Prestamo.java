package com.juanalejop.biblioteca.model;

import java.time.LocalDate;
import com.juanalejop.biblioteca.util.Estado;

/**
 * Representa un préstamo de un libro en la biblioteca.
 * Guarda el libro prestado y la fecha en que se realizó el préstamo.
 */
public class Prestamo {
    private final  Libro libro;
    private final LocalDate fechaPrestamo;

    /**
     * Crea un nuevo préstamo para el libro especificado.
     * Cambia automáticamente el estado del libro a PRESTADO.
     *
     * @param libro el libro que se va a prestar (no puede ser null)
     * @throws IllegalArgumentException si el libro es null
     */
    public Prestamo(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        libro.cambiarEstado(Estado.PRESTADO);
    }

    /**
     * @return el libro prestado
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @return la fecha en que se realizó el préstamo
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
}