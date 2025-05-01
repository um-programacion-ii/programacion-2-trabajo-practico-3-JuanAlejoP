package com.juanalejop.biblioteca.service;

import com.juanalejop.biblioteca.model.Catalogo;
import com.juanalejop.biblioteca.model.Libro;
import com.juanalejop.biblioteca.model.Prestamo;
import com.juanalejop.biblioteca.util.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para {@link SistemaPrestamos}, usando Mockito para simular el catálogo.
 */
@ExtendWith(MockitoExtension.class)
public class SistemaPrestamosTest {
    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    /**
     * Verifica que prestarLibro crea un {@link Prestamo} y cambia el estado del libro a PRESTADO,
     * además de invocar al catálogo exactamente una vez.
     */
    @Test
    void testPrestarLibro_Exitoso() {
        // Preparamos un libro disponible
        Libro libro = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        when(catalogo.buscarPorIsbn("979-8888771389"))
                .thenReturn(Optional.of(libro));

        // Ejecución
        Prestamo prestamo = sistemaPrestamos.prestarLibro("979-8888771389");

        // Aserciones
        assertNotNull(prestamo, "El Prestamo proporcionado es null");
        assertEquals(Estado.PRESTADO, libro.getEstado(), "El estado del libro no pasó a PRESTADO");

        // Verificación de interacción con el mock
        verify(catalogo, times(1)).buscarPorIsbn("979-8888771389");
    }

    /**
     * Verifica que prestarLibro lanza NoSuchElementException si el libro no existe.
     */
    @Test
    void testPrestarLibro_NoExiste() {
        when(catalogo.buscarPorIsbn("0000-0-00-000000-0"))
                .thenReturn(Optional.empty());

        // Debe lanzar excepción por no existir el libro
        assertThrows(NoSuchElementException.class, () ->
                sistemaPrestamos.prestarLibro("0000-0-00-000000-0"),
                "Debería lanzar NoSuchElementException si el libro no existe");

        // Verificamos que se llamó a buscarPorIsbn
        verify(catalogo).buscarPorIsbn("0000-0-00-000000-0");
    }

    /**
     * Verifica que devolverLibro cambia el estado del libro a DISPONIBLE
     * y que invoca al catálogo para buscarlo.
     */
    @Test
    void testDevolverLibro_Exitoso() {
        Libro libro = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");

        // Ponemos manualmente el libro en estado PRESTADO
        libro.cambiarEstado(Estado.PRESTADO);
        when(catalogo.buscarPorIsbn("979-8888771389"))
                .thenReturn(Optional.of(libro));

        // Ejecución
        sistemaPrestamos.devolverLibro("979-8888771389");

        // Aserción del cambio de estado
        assertEquals(Estado.DISPONIBLE, libro.getEstado(), "El estado del libro no pasó a DISPONIBLE");

        // Verificación de interacción con el mock
        verify(catalogo).buscarPorIsbn("979-8888771389");
    }

    /**
     * Verifica que devolverLibro lanza IllegalStateException si el libro
     * no está en estado PRESTADO al intentar devolverlo.
     */
    @Test
    void testDevolverLibro_NoPrestado() {
        Libro libro = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        when(catalogo.buscarPorIsbn("979-8888771389"))
                .thenReturn(Optional.of(libro));

        // Debe lanzar excepción porque el libro no estaba prestado
        assertThrows(IllegalStateException.class, () ->
            sistemaPrestamos.devolverLibro("979-8888771389"),
                "Debería lanzar IllegalStateException si el libro no está PRESTADO");

        // Verificación de interacción
        verify(catalogo).buscarPorIsbn("979-8888771389");
    }
}