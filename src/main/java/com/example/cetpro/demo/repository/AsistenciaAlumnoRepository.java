package com.example.cetpro.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cetpro.demo.entity.AsistenciaAlumnoEntity;

@Repository
public interface AsistenciaAlumnoRepository extends JpaRepository<AsistenciaAlumnoEntity, Long>{

}
