/**
 * Representa un libro en el sistema de biblioteca.
 * Contiene información básica como ISBN, título, autor y estado de disponibilidad.
 */
package com.juanalejop.biblioteca.model;

import com.juanalejop.biblioteca.util.Estado;

public class Libro {
    /** Código ISBN único del libro. */
    private final String isbn;

    /** Título del libro. */
    private final String titulo;

    /** Nombre del autor del libro. */
    private final String autor;

    /** Estado actual de disponibilidad del libro. */
    private Estado estado;

    /**
     * Crea un nuevo libro con estado inicial DISPONIBLE.
     *
     * @param isbn   Identificador ISBN del libro. No puede ser null ni vacío.
     * @param titulo Título del libro. No puede ser null ni vacío.
     * @param autor  Autor del libro. No puede ser null ni vacío.
     * @throws IllegalArgumentException si cualquiera de los parámetros es null o vacío.
     */
    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = Estado.DISPONIBLE;
    }

    /**
     * @return Código ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return Nombre del autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @return Estado actual de disponibilidad del libro.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Cambia el estado del libro al indicado.
     *
     * @param nuevoEstado Nuevo estado del libro (DISPONIBLE o PRESTADO).
     * @throws IllegalArgumentException si nuevoEstado es null.
     */
    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }
}