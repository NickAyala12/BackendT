package com.turismobarranca.backendt.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "boletos", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"viaje_id", "numero_asiento"})
})
@Data
public class boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    private viaje viajeAsignado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private usuario cliente;

    @Column(name = "numero_asiento", nullable = false, length = 3)
    private String numeroAsiento;

    @Column(name = "metodo_pago", nullable = false, length = 30)
    private String metodoPago;

    @Column(name = "monto_pagado", nullable = false)
    private Double montoPagado;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra = LocalDateTime.now();
}