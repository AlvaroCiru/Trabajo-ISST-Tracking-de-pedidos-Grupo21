package es.upm.dit.isst.backend.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    private int id;
    // @NotBlank
    private String nombre;
    // @NotBlank
    private String apellido;
    // @Email
    // @NotBlank
    private String email;
    // @NotBlank
    private String contrasena;
    // @NotBlank
    // @Size(max = 12)
    private String telefono;
    // @NotNull
    private boolean esGestor;
    private int empresa;
    
}
