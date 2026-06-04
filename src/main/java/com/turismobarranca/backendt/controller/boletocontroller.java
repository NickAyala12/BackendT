package com.turismobarranca.backendt.controller;

import com.turismobarranca.backendt.model.boleto;
import com.turismobarranca.backendt.service.boletoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boletos")
public class boletocontroller {

    @Autowired
    private boletoservice boletoService;

    // Endpoint para comprar/reservar un pasaje
    @PostMapping("/comprar")
    public ResponseEntity<?> procesarCompra(@RequestBody boleto nuevoBoleto) {
        try {
            boleto resultado = boletoService.comprarBoleto(nuevoBoleto);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            // Si el bus está lleno o el viaje no existe, atrapa la excepción del servicio y manda el error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}