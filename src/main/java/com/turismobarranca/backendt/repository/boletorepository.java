package com.turismobarranca.backendt.repository;

import com.turismobarranca.backendt.model.boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface boletorepository extends JpaRepository<boleto, Long> {
    // Cuenta cuántos asientos ya se vendieron en un viaje específico
    long countByViajeAsignadoId(Long viajeId);
}