package com.turismobarranca.backendt.controller;

import com.turismobarranca.backendt.model.usuario;
import com.turismobarranca.backendt.repository.assignmentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class usuariocontroller {

    @Autowired
    private assignmentrepository usuarioRepo;

    // Endpoint para registrar un nuevo cliente
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody usuario nuevoUsuario) {
        if (usuarioRepo.findByDni(nuevoUsuario.getDni()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El DNI ya se encuentra registrado.");
        }
        if (usuarioRepo.findByCorreo(nuevoUsuario.getCorreo()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El correo ya está en uso.");
        }
        
        usuario guardado = usuarioRepo.save(nuevoUsuario);
        return ResponseEntity.ok(guardado);
    }

    // Endpoint para el Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String contrasena = credenciales.get("contrasena");

        Optional<usuario> userOpt = usuarioRepo.findByCorreo(correo);
        
        if (userOpt.isPresent() && userOpt.get().getContrasena().equals(contrasena)) {
            return ResponseEntity.ok(userOpt.get()); // Retorna el usuario completo con su ROL
        }
        
        return ResponseEntity.status(401).body("Credenciales incorrectas o usuario no encontrado.");
    }

    // Simulación de la API RENIEC en el Backend
    @GetMapping("/reniec/{dni}")
    public ResponseEntity<?> consultarReniec(@PathVariable String dni) {
        if (dni.length() != 8) {
            return ResponseEntity.badRequest().body("El DNI debe tener 8 dígitos.");
        }
        // Simulamos la respuesta de la RENIEC mapeando el DNI a un nombre ficticio para las pruebas
        String nombreSimulado = "AYALA NICK";
        return ResponseEntity.ok(Map.of("dni", dni, "nombreCompleto", nombreSimulado));
    }
}