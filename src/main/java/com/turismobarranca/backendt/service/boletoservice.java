package com.turismobarranca.backendt.service;

import com.turismobarranca.backendt.model.boleto;
import com.turismobarranca.backendt.model.viaje;
import com.turismobarranca.backendt.repository.boletorepository;
import com.turismobarranca.backendt.repository.viajerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class boletoservice {

    @Autowired
    private boletorepository boletoRepo;

    @Autowired
    private viajerepository viajeRepo;

    public boleto comprarBoleto(boleto nuevoBoleto) {
        // 1. Obtener los datos completos del viaje
        viaje v = viajeRepo.findById(nuevoBoleto.getViajeAsignado().getId())
                .orElseThrow(() -> new RuntimeException("El viaje seleccionado no existe."));

        // 2. Contar cuántos asientos ya se vendieron para este viaje
        long asientosVendidos = boletoRepo.countByViajeAsignadoId(v.getId());

        // 3. Validar si el bus ya alcanzó el límite de su capacidad
        if (asientosVendidos >= v.getBusAsignado().getCapacidad()) {
            throw new RuntimeException("Lo sentimos, el bus para este horario ya se encuentra lleno.");
        }

        // 4. Si hay espacio, se procesa y guarda el boleto
        return boletoRepo.save(nuevoBoleto);
    }
}