package es.upm.dit.isst.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
    private String pedidoId;
    private int vehiculoId;
}
