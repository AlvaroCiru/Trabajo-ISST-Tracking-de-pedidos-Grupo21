package es.upm.dit.isst.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import es.upm.dit.isst.backend.enums.EstadoPedido;
import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString
public class Pedido {
    @Id
    private String codigo;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Column(name = "fecha_crecion", nullable = false)
    private LocalDate fecha_creacion;

    @Column(name = "hora_creacion", nullable = false)
    private LocalTime hora_creacion;

    @Column(name = "estado", nullable = false)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "origen_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Direccion origen;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Direccion destino;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}
