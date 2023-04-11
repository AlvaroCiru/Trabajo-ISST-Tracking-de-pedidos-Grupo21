package es.upm.dit.isst.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.Max;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {
    @Id
    private String codigo;
    // @NotBlank
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    // @NotNull
    // @Max(2)
    private int estado;
    // @NotNull
    private int vehiculo;
    private int usuario;
    // @NotNull
    private int empresa;
    // @NotNull
    private int origen;
    // @NotNull
    private int destino;
}
