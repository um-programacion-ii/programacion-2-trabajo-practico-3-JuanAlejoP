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

@ExtendWith(MockitoExtension.class)
public class SistemaPrestamosTest {
    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @Test
    void testPrestarLibro_Exitoso() {
        Libro libro = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        when(catalogo.buscarPorIsbn("979-8888771389"))
                .thenReturn(Optional.of(libro));
        Prestamo prestamo = sistemaPrestamos.prestarLibro("979-8888771389");
    assertNotNull(prestamo, "El Prestamo proporcionado es null");
    assertEquals(Estado.PRESTADO, libro.getEstado(), "El estado del libro no pasó a PRESTADO");
    verify(catalogo, times(1)).buscarPorIsbn("979-8888771389");
    }

    @Test
    void testPrestarLibro_NoExiste() {
        when(catalogo.buscarPorIsbn("0000-0-00-000000-0"))
                .thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
                sistemaPrestamos.prestarLibro("0000-0-00-000000-0"),
                "Debería lanzar NoSuchElementException si el libro no existe");
        verify(catalogo).buscarPorIsbn("0000-0-00-000000-0");
    }

    @Test
    void testDevolverLibro_Exitoso() {
        Libro libro = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        libro.cambiarEstado(Estado.PRESTADO);
        when(catalogo.buscarPorIsbn("979-8888771389"))
                .thenReturn(Optional.of(libro));
        sistemaPrestamos.devolverLibro("979-8888771389");
        assertEquals(Estado.DISPONIBLE, libro.getEstado(), "El estado del libro no pasó a DISPONIBLE");
        verify(catalogo).buscarPorIsbn("979-8888771389");
    }

    @Test
    void testDevolverLibro_NoPrestado() {
        Libro libro = new Libro ("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        when(catalogo.buscarPorIsbn("979-8888771389"))
                .thenReturn(Optional.of(libro));
        assertThrows(IllegalStateException.class, () ->
            sistemaPrestamos.devolverLibro("979-8888771389"),
                "Debería lanzar IllegalStateException si el libro no está PRESTADO");
        verify(catalogo).buscarPorIsbn("979-8888771389");
    }
}