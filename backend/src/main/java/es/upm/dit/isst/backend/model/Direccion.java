package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Direccion {
    @Id
    private int id;

    @NotNull
    private double latitud;
    @NotNull
    private double longitud;

    @NotBlank
    private String provincia;

    @NotBlank
    private String ciudad;

    @NotNull
    @Size(min = 5, max = 5)
    private String postalCode;

    @NotBlank
    private String calle;
    @NotNull
    private int numero;

    private String vivienda;
}
