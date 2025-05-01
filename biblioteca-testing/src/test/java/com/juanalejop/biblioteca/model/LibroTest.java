package com.juanalejop.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.juanalejop.biblioteca.util.Estado;

/**
 * Pruebas unitarias para la clase Libro.
 */
public class LibroTest {
    private Libro libro;

    /**
     * Inicializa un libro válido antes de cada test.
     */
    @BeforeEach
    void setUp() {
        libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    }

    /**
     * Verifica que el constructor inicializa correctamente todos los atributos.
     */
    @Test
    void testCrearLibroValido() {
        assertEquals("978-3-16-148410-0", libro.getIsbn());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    /**
     * Verifica que cambiar a estado PRESTADO actualiza correctamente el estado del libro.
     */
    @Test
    void testCambiarEstadoPrestado() {
        libro.cambiarEstado(Estado.PRESTADO);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    /**
     * Verifica que cambiar de vuelta a DISPONIBLE funciona después de estar prestado.
     */
    @Test
    void testCambiarEstadoDisponible() {
        libro.cambiarEstado(Estado.PRESTADO);
        libro.cambiarEstado(Estado.DISPONIBLE);
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }
}