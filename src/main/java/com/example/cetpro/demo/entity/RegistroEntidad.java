package com.example.cetpro.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "registro_entidad")
public class RegistroEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellidosNombres;
    private String dni;
    private Integer edad;
    private String direccion;
    private String telefono;
    private String correo;
    private String especialidad;
    private String turno;
}
