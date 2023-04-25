package es.upm.dit.isst.backend.model;

import lombok.*;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @NotBlank
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String contrasena;

    @Column(name = "telefono", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @NotBlank
    private String telefono;

    @Column(name = "es_gestor", nullable = false)
    private boolean es_gestor;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Empresa empresa;
}
