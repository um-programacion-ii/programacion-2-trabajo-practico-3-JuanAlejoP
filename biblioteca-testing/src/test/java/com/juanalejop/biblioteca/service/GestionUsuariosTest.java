package com.juanalejop.biblioteca.service;

import com.juanalejop.biblioteca.model.Prestamo;
import com.juanalejop.biblioteca.model.Libro;
import com.juanalejop.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Pruebas unitarias para {@link GestionUsuarios}, usando Mockito
 * para simular el servicio de préstamos.
 */
@ExtendWith(MockitoExtension.class)
public class GestionUsuariosTest {
    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    /**
     * Prepara un usuario base antes de cada test.
     */
    @BeforeEach
    void setUp() {
        gestionUsuarios.registrarUsuario("usuario1");
    }

    /**
     * Verifica que al registrar un usuario nuevo,
     * este aparece en el mapa de usuarios.
     */
    @Test
    void testRegistrarUsuario() {
        assertFalse(gestionUsuarios.getUsuarios().containsKey("usuario2"));
        gestionUsuarios.registrarUsuario("usuario2");
        assertTrue(gestionUsuarios.getUsuarios().containsKey("usuario2"));
    }

    /**
     * Verifica el flujo exitoso de registrar un préstamo:
     * - Mockito simula el préstamo devuelto.
     * - El método invoca al sistema de préstamos.
     * - El historial del usuario crece en uno con el objeto simulado.
     */
    @Test
    void testRegistrarPrestamo_Exitoso() {
        Libro libro = new Libro("979-8888771389", "The Fragrant Flower Blooms with Dignity (1)", "Saka Mikami");
        Prestamo prestamoMock = new Prestamo(libro);
        when(sistemaPrestamos.prestarLibro("979-8888771389"))
                .thenReturn(prestamoMock);
        gestionUsuarios.registrarPrestamo("usuario1", "979-8888771389");
        verify(sistemaPrestamos).prestarLibro("979-8888771389");
        Usuario usuario = gestionUsuarios.getUsuarios().get("usuario1");
        assertEquals(1, usuario.getHistorialPrestamo().size(),
                "El historial de préstamos del usuario debería haber crecido en 1");
        assertSame(prestamoMock, usuario.getHistorialPrestamo().get(0),
                "El préstamo almacenado debería ser el mismo devuelto por el mock");
    }

    /**
     * Verifica que registrar un préstamo para un usuario inexistente
     * lanza NoSuchElementException y no interactúa con el servicio.
     */
    @Test
    void testRegistrarPrestamo_SinUsuario() {
        assertThrows(NoSuchElementException.class, () ->
                gestionUsuarios.registrarPrestamo("usuarioX", "979-8888771389"),
                "Debería lanzar NoSuchElementException al no encontrar al usuario");
        verifyNoInteractions(sistemaPrestamos);
    }
}