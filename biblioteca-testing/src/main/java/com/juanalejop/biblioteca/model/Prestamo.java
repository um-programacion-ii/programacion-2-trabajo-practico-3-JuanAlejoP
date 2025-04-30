package com.juanalejop.biblioteca.model;

import java.time.LocalDate;
import com.juanalejop.biblioteca.util.Estado;

public class Prestamo {
    private final  Libro libro;
    private final LocalDate fechaPrestamo;

    public Prestamo(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        libro.cambiarEstado(Estado.PRESTADO);
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
}