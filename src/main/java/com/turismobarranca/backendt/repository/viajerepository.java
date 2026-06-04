package com.turismobarranca.backendt.repository;

import com.turismobarranca.backendt.model.viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface viajerepository extends JpaRepository<viaje, Long> {
    // Para buscar viajes por origen, destino y fecha tal como lo hace el cliente
    List<viaje> findByOrigenAndDestinoAndFecha(String origen, String destino, LocalDate fecha);
}