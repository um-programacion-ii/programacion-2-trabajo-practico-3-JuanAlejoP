package com.juanalejop.biblioteca.service;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import com.juanalejop.biblioteca.model.Usuario;
import com.juanalejop.biblioteca.model.Prestamo;

public class GestionUsuarios {
    private final SistemaPrestamos sistemaPrestamos;
    private final Map<String, Usuario> usuarios;

    public GestionUsuarios(SistemaPrestamos sistemaPrestamos) {
        if (sistemaPrestamos == null) {
            throw new IllegalArgumentException("El servicio de préstamos no puede ser null");
        }
        this.sistemaPrestamos = sistemaPrestamos;
        this.usuarios = new HashMap<>();
    }

    public void registrarUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser null o vacío");
        }
        if (usuarios.containsKey(nombreUsuario)) {
            throw new IllegalStateException("El usuario ya existe: " + nombreUsuario);
        }
        usuarios.put(nombreUsuario, new Usuario(nombreUsuario));
    }

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

    public Map<String, Usuario> getUsuarios() {
        return Collections.unmodifiableMap(usuarios);
    }
}