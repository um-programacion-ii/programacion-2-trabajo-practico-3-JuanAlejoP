package com.juanalejop.biblioteca.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.juanalejop.biblioteca.model.Catalogo;
import com.juanalejop.biblioteca.model.Libro;
import com.juanalejop.biblioteca.model.Prestamo;
import com.juanalejop.biblioteca.util.Estado;

/**
 * Servicio que orquesta la lógica de préstamos y devoluciones de libros,
 * delegando la búsqueda de libros al {@link Catalogo}.
 */
public class SistemaPrestamos {
    private final Catalogo catalogo;

    /**
     * Constructor que inyecta la dependencia al catálogo de libros.
     *
     * @param catalogo catálogo usado para buscar los libros (no puede ser null)
     * @throws IllegalArgumentException si el catálogo es null
     */
    public SistemaPrestamos(Catalogo catalogo) {
        if (catalogo == null) {
            throw new IllegalArgumentException("El catálogo proporcionado es null");
        }
        this.catalogo = catalogo;
    }

    /**
     * Realiza el préstamo de un libro identificado por su ISBN.
     *
     * @param isbn ISBN del libro a prestar (no puede ser null ni vacío)
     * @return un objeto {@link Prestamo} que registra la operación
     * @throws NoSuchElementException si no se encuentra el libro en el catálogo
     * @throws IllegalStateException  si el libro no está en estado DISPONIBLE
     */
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

    /**
     * Realiza la devolución de un libro identificado por su ISBN.
     *
     * @param isbn ISBN del libro a devolver (no puede ser null ni vacío)
     * @throws NoSuchElementException si no se encuentra el libro en el catálogo
     * @throws IllegalStateException  si el libro no está en estado PRESTADO
     */
    public void devolverLibro(String isbn) {
        Optional<Libro> busqueda = catalogo.buscarPorIsbn(isbn);
        if (busqueda.isEmpty()) {
            throw new NoSuchElementException("Libro no encontrado: " + isbn);
        }
        Libro encontrado = busqueda.get();
        if (encontrado.getEstado() != Estado.PRESTADO) {
            throw new IllegalStateException("El libro con ISBN " + isbn + " no está PRESTADO");
        }
        // Cambia el estado de vuelta a DISPONIBLE
        encontrado.cambiarEstado(Estado.DISPONIBLE);
    }
}