package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;
    private boolean es_gestor;
    private int empresa;
}
