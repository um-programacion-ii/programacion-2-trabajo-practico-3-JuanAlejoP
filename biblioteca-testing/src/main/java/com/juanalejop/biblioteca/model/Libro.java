package com.juanalejop.biblioteca.model;

import com.juanalejop.biblioteca.util.Estado;

public class Libro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private Estado estado;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = Estado.DISPONIBLE;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Estado getEstado() {
        return estado;
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }
}