package com.example.cetpro.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cetpro.demo.entity.RegistroEntidad;
import com.example.cetpro.demo.repository.RegistroRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class RegistrarRest {

    @Autowired
    private RegistroRepository repo;

    

    @PostMapping("/registrar")
    public Map<String, String> registrar(@RequestBody RegistroEntidad registroAlumno) {
          repo.save(registroAlumno);
          return Map.of("mensaje", "Registro guardado exitosamente");
    }
}
