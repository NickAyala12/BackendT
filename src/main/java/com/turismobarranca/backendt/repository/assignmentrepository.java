package com.turismobarranca.backendt.repository;

import com.turismobarranca.backendt.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface assignmentrepository extends JpaRepository<usuario, Long> {
    Optional<usuario> findByCorreo(String correo);
    Optional<usuario> findByDni(String dni);
}