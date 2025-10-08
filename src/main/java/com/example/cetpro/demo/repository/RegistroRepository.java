package com.example.cetpro.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cetpro.demo.entity.RegistroEntidad;

public interface RegistroRepository extends JpaRepository<RegistroEntidad, Long>  {
    boolean existsByDni(String dni);
    Optional<RegistroEntidad> findTopByDniOrderByIdDesc(String dni);
}
