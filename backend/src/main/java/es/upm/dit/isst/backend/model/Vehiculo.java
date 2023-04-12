package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehiculos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehiculo {
    @Id
    private int id;
    private String matricula;
    private String modelo;
    private String telefono_vehiculo;
}

