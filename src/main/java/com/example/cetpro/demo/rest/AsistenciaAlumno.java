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
import com.example.cetpro.demo.entity.ConfigFlags;
import com.example.cetpro.demo.entity.RegistroEntidad;
import com.example.cetpro.demo.repository.AsistenciaAlumnoRepository;
import com.example.cetpro.demo.repository.ConfigFlagRepository;
import com.example.cetpro.demo.repository.RegistroRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/asistencia")
public class AsistenciaAlumno {

    @Autowired
    private AsistenciaAlumnoRepository asistenciaAlumnoRepository;
    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private ConfigFlagRepository configFlagRepository;

    @PostMapping("/guardar")
    public Map<String, String> guardarQR(@RequestBody Map<String, String> datos) {
        String contenido = datos.get("contenido");
        Boolean existeDni = registroRepository.existsByDni(contenido);
        boolean permitido = configFlagRepository
                .findByNombre("acceso_logica")
                .map(ConfigFlags::getHabilitado)
                .orElse(false); 
        String as = configFlagRepository
                .findByNombre("acceso_logica").get().getAccountsid();
        String at = configFlagRepository
                .findByNombre("acceso_logica").get().getAuthtok();
        String nombre = registroRepository.findTopByDniOrderByIdDesc(contenido).map(RegistroEntidad::getApellidosNombres)
        .orElse("No encontrado");;
    
        if (contenido == null || contenido.isBlank()) {
            return Map.of("mensaje", "Registro vacio");
        }
        if (!existeDni) {
            return Map.of("mensaje", "El DNI ingresado no existe!");
        }
        
        AsistenciaAlumnoEntity qr = new AsistenciaAlumnoEntity();
        qr.setContenido(contenido);
        asistenciaAlumnoRepository.save(qr);

        //Logica para enviar mensaje cuando el alumno asistio al colegio
        if(permitido){
            Twilio.init(as, at);
            Message messageMaster = Message.creator(
              new PhoneNumber("whatsapp:+51997315856"),
              new PhoneNumber("whatsapp:+14155238886"),
              "El alumno " + nombre + "("+ contenido + ")" + " acaba de registrar asistencia 😁").create();
                    
           return Map.of("mensaje", "Registro guardado exitosamente");
        }else{
            return Map.of("mensaje", "No se envio mensaje por flag");
        }
    }
}
