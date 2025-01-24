package com.example.radiant.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Usuario;
import com.example.radiant.Repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioActualizado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setApellido(usuario.getApellido());
        usuarioActualizado.setCorreo(usuario.getCorreo());
        usuarioActualizado.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioActualizado.setRol(usuario.getRol());
        return usuarioRepository.save(usuarioActualizado);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }
}
