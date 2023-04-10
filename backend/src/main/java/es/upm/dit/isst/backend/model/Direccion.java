package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Direccion {
    @Id
    private int id;
    private double latitud;
    private double longitud;
    private String provincia;
    private String ciudad;
    private String postalCode;
    private String calleYnumero;
    private String vivienda;
}
