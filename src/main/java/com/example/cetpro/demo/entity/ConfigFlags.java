package com.example.cetpro.demo.entity;

import jakarta.persistence.Column;
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
@Table(name = "configFlags")
public class ConfigFlags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre = "acceso_logica"; // nombre del flag

    @Column(nullable = false)
    private Boolean habilitado = false; // true o false
    @Column(name = "accountsid")
    private String accountsid;
    @Column(name = "authtok")
    private String authtok;

}
