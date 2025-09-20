package com.example.cetpro.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cetpro.demo.entity.ConfigFlags;



public interface ConfigFlagRepository  extends JpaRepository<ConfigFlags, Long>{
    Optional<ConfigFlags> findByNombre(String nombre);
}
