package es.upm.dit.isst.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Trazas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Traza {
    @Id
    private int id;
    private double latitud;
    private double longitud;
    private LocalDate fecha_creacion;
    private LocalTime hora_creacion;
    private String pedido_id;
    private int vehiculo_id;
}
