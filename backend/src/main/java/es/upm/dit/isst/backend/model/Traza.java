package es.upm.dit.isst.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Trazas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Traza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "logitud", nullable = false)
    private double longitud;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fecha_creacion;

    @Column(name = "hora_creacion", nullable = false)
    private LocalTime hora_creacion;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Vehiculo vehiculo;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitud);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitud);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Traza other = (Traza) obj;
        if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
            return false;
        if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
            return false;
        return true;
    }


}
