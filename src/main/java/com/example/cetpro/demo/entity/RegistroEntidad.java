package com.example.cetpro.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registro_entidad")
@EntityListeners(AuditingEntityListener.class)
public class RegistroEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo apellidosNombres es obligatorio")
    private String apellidosNombres;
    private String dni;
    @NotNull(message = "El campo Edad es obligatorio")
    private Integer edad;
    private String direccion;
    @NotBlank(message = "El campo apellidosNombres es obligatorio")
    private String telefono;
    @NotBlank(message = "El campo apellidosNombres es obligatorio")
    private String correo;
    @NotBlank(message = "El campo apellidosNombres es obligatorio")
    private String especialidad;
    @NotBlank(message = "El campo apellidosNombres es obligatorio")
    private String turno;

    private String mensaje;
    
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
