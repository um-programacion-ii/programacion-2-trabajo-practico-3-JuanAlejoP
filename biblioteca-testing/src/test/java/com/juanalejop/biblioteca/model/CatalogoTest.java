package com.juanalejop.biblioteca.model;

import com.juanalejop.biblioteca.util.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogoTest {
    private Catalogo catalogo;
    private Libro libro1, libro2, libro3;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro2 = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");
        libro3 = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        libro3.cambiarEstado(Estado.PRESTADO);
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
        catalogo.agregarLibro(libro3);
    }

    @Test
    void testBuscarPorIsbn_EncontradoDisponible() {
        Optional<Libro> libroDisponible = catalogo.buscarPorIsbn("978-3-16-148410-0");
        assertTrue(libroDisponible.isPresent(), "El libro con ISBN 978-3-16-148410-0 debería existir");
        Libro encontradoDisponible = libroDisponible.get();
        assertEquals("Clean Code", encontradoDisponible.getTitulo());
        assertEquals(Estado.DISPONIBLE, encontradoDisponible.getEstado(), "El libro debería tener estado DISPONIBLE");
    }

    @Test
    void testBuscarPorIsbn_EncontradoPrestado() {
        Optional<Libro> libroPrestado = catalogo.buscarPorIsbn("979-8888771389");
        assertTrue(libroPrestado.isPresent(), "El libro con ISBN 979-8888771389 debería existir");
        Libro encontradoPrestado = libroPrestado.get();
        assertEquals("The Fragrant Flower Blooms with Dignity (1)", encontradoPrestado.getTitulo());
        assertEquals(Estado.PRESTADO, encontradoPrestado.getEstado(), "El libro debería tener estado PRESTADO");
    }

    @Test
    void testBuscarPorIsbn_NoEncontrado() {
        Optional<Libro> libro = catalogo.buscarPorIsbn("000-0-00-000000-0");
        assertFalse(libro.isPresent(), "El libro con ISBN 000-0-00-000000-0 no debería existir");
    }

    @Test
    void testObtenerDisponibles() {
        List<Libro> disponibles = catalogo.obtenerDisponibles();
        assertEquals(2, disponibles.size(), "Debería haber exactamente 2 libros disponibles");
        assertTrue(disponibles.contains(libro1));
        assertTrue(disponibles.contains(libro2));
        assertFalse(disponibles.contains(libro3));
    }

    @Test
    void testCatalogoVacio() {
        Catalogo vacio = new Catalogo();
        assertTrue(vacio.buscarPorIsbn("000-0-00-000000-0").isEmpty());
        assertTrue(vacio.obtenerDisponibles().isEmpty());
    }
}