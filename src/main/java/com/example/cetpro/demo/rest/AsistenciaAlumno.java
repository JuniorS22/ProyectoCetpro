package com.example.cetpro.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cetpro.demo.entity.AsistenciaAlumnoEntity;
import com.example.cetpro.demo.repository.AsistenciaAlumnoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/asistencia")
public class AsistenciaAlumno {

    @Autowired
    private AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarQR(@RequestBody Map<String, String> datos) {
        String contenido = datos.get("contenido");
        if (contenido == null || contenido.isBlank()) {
            return ResponseEntity.badRequest().body("QR vacío o inválido");
        }
        System.out.println("📩 Contenido recibido: " + contenido);

        AsistenciaAlumnoEntity qr = new AsistenciaAlumnoEntity();
        qr.setContenido(contenido);
        asistenciaAlumnoRepository.save(qr);

        return ResponseEntity.ok("QR guardado correctamente en la base de datos");
    }
}
