package com.turismobarranca.backendt.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "viajes")
@Data
public class viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String origen;

    @Column(nullable = false, length = 100)
    private String destino;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_salida", nullable = false, length = 20)
    private String horaSalida;

    @Column(nullable = false)
    private Double precio;

    // Relación Muchos a Uno: Muchos viajes pueden usar el mismo bus
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private bus busAsignado;

    @ManyToOne
    @JoinColumn(name = "chofer_id")
    private usuario chofer;

    @ManyToOne
    @JoinColumn(name = "terramoza_id")
    private usuario terramoza;
}