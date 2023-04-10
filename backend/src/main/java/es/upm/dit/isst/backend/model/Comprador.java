package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comprador {
    @Id
    private int id;

    @ManyToOne
    private Usuario usuario;
}
