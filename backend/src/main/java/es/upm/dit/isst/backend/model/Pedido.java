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
import jakarta.persistence.Table;

@Entity
@Table(name = "Pedidos")
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
    private LocalDate fecha_creacion;
    private LocalTime hora_creacion;
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
