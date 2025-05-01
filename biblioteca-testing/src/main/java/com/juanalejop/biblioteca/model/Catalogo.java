/**
 * Gestiona una colección de libros en el sistema de biblioteca.
 * Permite agregar libros, buscar por ISBN y obtener la lista de libros disponibles.
 */
package com.juanalejop.biblioteca.model;

import com.juanalejop.biblioteca.util.Estado;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Catalogo {
    /**
     * Lista interna de libros almacenados en el catálogo.
     */
    private final List<Libro> libros;

    /**
     * Crea un nuevo catálogo vacío.
     */
    public Catalogo() {
        this.libros = new ArrayList<>();
    }

    /**
     * Agrega un libro al catálogo.
     *
     * @param libro Libro a agregar. No puede ser null.
     * @throws IllegalArgumentException si libro es null.
     */
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn ISBN del libro a buscar. No puede ser null.
     * @return Optional conteniendo el libro si se encuentra, o vacío en caso contrario.
     */
    public Optional<Libro> buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

    /**
     * Obtiene la lista de libros que actualmente están disponibles.
     *
     * @return Lista de libros en estado DISPONIBLE. Puede estar vacía.
     */
    public List<Libro> obtenerDisponibles() {
        return libros.stream()
                .filter(l -> l.getEstado() == Estado.DISPONIBLE)
                .toList();
    }
}