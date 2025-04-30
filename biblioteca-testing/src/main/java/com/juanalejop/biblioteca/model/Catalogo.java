package com.juanalejop.biblioteca.model;

import com.juanalejop.biblioteca.util.Estado;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Catalogo {
    private final List<Libro> libros;

    public Catalogo() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Optional<Libro> buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Libro> obtenerDisponibles() {
        return libros.stream()
                .filter(l -> l.getEstado() == Estado.DISPONIBLE)
                .toList();
    }
}