package com.juanalejop.biblioteca.service;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import com.juanalejop.biblioteca.model.Usuario;
import com.juanalejop.biblioteca.model.Prestamo;

/**
 * Servicio para gestionar el registro de usuarios y
 * la asociación de préstamos a dichos usuarios.
 */
public class GestionUsuarios {
    private final SistemaPrestamos sistemaPrestamos;
    private final Map<String, Usuario> usuarios;

    /**
     * Constructor que inyecta la dependencia al servicio de préstamos.
     *
     * @param sistemaPrestamos servicio usado para crear préstamos (no puede ser null)
     * @throws IllegalArgumentException si el servicio es null
     */
    public GestionUsuarios(SistemaPrestamos sistemaPrestamos) {
        if (sistemaPrestamos == null) {
            throw new IllegalArgumentException("El servicio de préstamos no puede ser null");
        }
        this.sistemaPrestamos = sistemaPrestamos;
        this.usuarios = new HashMap<>();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombreUsuario identificador único del usuario (no puede ser null ni vacío)
     * @throws IllegalArgumentException si el nombre es null o vacío
     * @throws IllegalStateException    si ya existe un usuario con ese nombre
     */
    public void registrarUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser null o vacío");
        }
        if (usuarios.containsKey(nombreUsuario)) {
            throw new IllegalStateException("El usuario ya existe: " + nombreUsuario);
        }
        usuarios.put(nombreUsuario, new Usuario(nombreUsuario));
    }

    /**
     * Registra un préstamo para el usuario indicado.
     *
     * @param nombreUsuario nombre del usuario que realiza el préstamo
     * @param isbn          ISBN del libro a prestar (no puede ser null ni vacío)
     * @throws NoSuchElementException   si no existe el usuario
     * @throws IllegalArgumentException si el ISBN es null o vacío
     */
    public void registrarPrestamo(String nombreUsuario, String isbn) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario == null) {
            throw new NoSuchElementException("Usuario no encontrado: " + nombreUsuario);
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN no puede ser null o vacío");
        }
        Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
        usuario.agregarPrestamo(prestamo);
    }

    /**
     * @return un mapa inmutable con todos los usuarios registrados
     */
    public Map<String, Usuario> getUsuarios() {
        return Collections.unmodifiableMap(usuarios);
    }
}