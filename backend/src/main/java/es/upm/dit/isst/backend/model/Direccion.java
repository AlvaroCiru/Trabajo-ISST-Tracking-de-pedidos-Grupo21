package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Direcciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Direccion {
    @Id
    private int id;
    private double latitud;
    private double longitud;
    private String direccion;
    private String postal_code;
}
