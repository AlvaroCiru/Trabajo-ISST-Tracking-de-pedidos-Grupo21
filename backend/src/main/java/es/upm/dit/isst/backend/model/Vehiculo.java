package es.upm.dit.isst.backend.model;

import lombok.*;

import org.apache.commons.lang3.builder.EqualsExclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehiculos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "matricula", nullable = false)
    @EqualsAndHashCode.Include
    private String matricula;

    @Column(name = "modelo", nullable = true)
    private String modelo;

    @Column(name = "telefono", nullable = false)
    private String telefono;
}

