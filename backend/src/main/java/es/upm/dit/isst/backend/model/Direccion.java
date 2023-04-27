package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Direcciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "latitud", nullable = false)
    @EqualsAndHashCode.Include
    private double latitud;

    @Column(name = "longitud", nullable = false)
    @EqualsAndHashCode.Include
    private double longitud;

    @Column(name = "provincia", nullable = true)
    private String provincia;

    @Column(name = "ciudad", nullable = true)
    private String ciudad;

    @Column(name = "domicilio", nullable = false, unique = true)
    private String domicilio;

    @Column(name = "postal_code", nullable = true)
    private String postal_code;
}
