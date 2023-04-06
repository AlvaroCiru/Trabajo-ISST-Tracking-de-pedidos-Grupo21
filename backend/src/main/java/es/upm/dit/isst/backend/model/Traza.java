package es.upm.dit.isst.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Traza {
    @Id
    private int id;
    private double latitud;
    private double longitud;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Vehiculo vehiculo;
}
