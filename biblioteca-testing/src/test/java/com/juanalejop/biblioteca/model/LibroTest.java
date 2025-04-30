package com.juanalejop.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.juanalejop.biblioteca.util.Estado;

public class LibroTest {
    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    }

    @Test
    void testCrearLibroValido() {
        assertEquals("978-3-16-148410-0", libro.getIsbn());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testCambiarEstadoPrestado() {
        libro.cambiarEstado(Estado.PRESTADO);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    void testCambiarEstadoDisponible() {
        libro.cambiarEstado(Estado.PRESTADO);
        libro.cambiarEstado(Estado.DISPONIBLE);
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }
}