package com.juanalejop.biblioteca.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.juanalejop.biblioteca.model.Catalogo;
import com.juanalejop.biblioteca.model.Libro;
import com.juanalejop.biblioteca.model.Prestamo;
import com.juanalejop.biblioteca.util.Estado;

public class SistemaPrestamos {
    private final Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        if (catalogo == null) {
            throw new IllegalArgumentException("El catálogo proporcionado es null");
        }
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn) {
        Optional<Libro> busqueda = catalogo.buscarPorIsbn(isbn);
        if (busqueda.isEmpty()) {
            throw new NoSuchElementException("Libro no encontrado: " + isbn);
        }
        Libro encontrado = busqueda.get();
        if (encontrado.getEstado() != Estado.DISPONIBLE) {
            throw new IllegalStateException("El libro con ISBN " + isbn + " no está DISPONIBLE");
        }
        return new Prestamo(encontrado);
    }

    public void devolverLibro(String isbn) {
        Optional<Libro> busqueda = catalogo.buscarPorIsbn(isbn);
        if (busqueda.isEmpty()) {
            throw new NoSuchElementException("Libro no encontrado: " + isbn);
        }
        Libro encontrado = busqueda.get();
        if (encontrado.getEstado() != Estado.PRESTADO) {
            throw new IllegalStateException("El libro con ISBN " + isbn + " no está PRESTADO");
        }
        encontrado.cambiarEstado(Estado.DISPONIBLE);
    }
}