package com.example.radiant.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.radiant.Models.User;
import com.example.radiant.Service.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private UserService usuarioService;

    @GetMapping("/todos")
    public List<User> getTodos() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<User> usuario = usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<User> actualizarUsuario(@RequestBody User usuario, @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.updateUser(id, usuario));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
