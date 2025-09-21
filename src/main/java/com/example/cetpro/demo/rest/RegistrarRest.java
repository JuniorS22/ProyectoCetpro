 package com.example.cetpro.demo.rest;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cetpro.demo.entity.ConfigFlags;
import com.example.cetpro.demo.entity.RegistroEntidad;
import com.example.cetpro.demo.repository.ConfigFlagRepository;
import com.example.cetpro.demo.repository.RegistroRepository;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.resend.*;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class RegistrarRest {

    @Autowired
    private RegistroRepository repo;
    
    @Autowired
    private ConfigFlagRepository configFlagRepository;


    @PostMapping("/enviarWhatsapp")
    public Map<String, String> enviarWhatsapp(@RequestBody RegistroEntidad registroAlumno) {
        boolean permitido = configFlagRepository
                .findByNombre("acceso_logica")
                .map(ConfigFlags::getHabilitado)
                .orElse(false); 
        String as = configFlagRepository
                .findByNombre("acceso_logica").get().getAccountsid();
        String at = configFlagRepository
                .findByNombre("acceso_logica").get().getAuthtok();

        if(permitido){
            Twilio.init(as, at);
            Message messageMaster = Message.creator(
              new PhoneNumber("whatsapp:+51997315856"),
              new PhoneNumber("whatsapp:+14155238886"),
              "Hola Maestro!, alerta de inscripción:\n" +
                      "*Programa de estudio:* " + registroAlumno.getEspecialidad() + "\n" +
                    "*Nombres:* " + registroAlumno.getApellidosNombres() + "\n" +
                    "*Número de alumno:* +51" + registroAlumno.getTelefono() + "\n" +
                    "*Turno:* " + registroAlumno.getTurno() + "\n" +
                    "*Correo:* " + registroAlumno.getCorreo() + "\n\n" +
                    "*Comunícate con él y dale más detalle por favor*").create();
            /* Message messageAlumn = Message.creator(
                        new PhoneNumber("whatsapp:+51" + registroAlumno.getTelefono()),
                        new PhoneNumber("whatsapp:+14155238886"),
                        "Hola estimado " + registroAlumno.getApellidosNombres() + " en unos min se comunicaran contigo por tu solicitud al programa: " + registroAlumno.getEspecialidad()).create();*/
            return Map.of("mensaje", "Mensaje enviado correctamente");
        }else{
            return Map.of("mensaje", "No se envio mensaje por flag");
        }
    }

    @PostMapping("/enviar-correo")
    public Map<String, String> registrarCorreo(@RequestBody RegistroEntidad consulta) {
        //Resend resend = new Resend("re_5sQmdo6j_A8MhbVJVwLYWGga8kN6DdRAf");mio
        Resend resend = new Resend("re_jGLrrAuR_G8LonVVru6yZK7wRoBcFvpqH");
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
        .from("onboarding@resend.dev")
        .to("wendy1990mjl@gmail.com")
        .subject("⚠️⚠️⚠️ Consulta enviada desde la Pagina")
        .html("<p>Datos: </p><strong>correo: "+ consulta.getCorreo()+" </strong></p>" + "\n" + "<p><strong> Nombres : " +consulta.getApellidosNombres()+"</strong></p>" +"<p><strong> Mensaje: "+ consulta.getMensaje() + "</strong></p>")
        .build();

        SendEmailResponse data = resend.emails().send(sendEmailRequest);
        return Map.of("mensaje", "Ok");
    }
    @PostMapping("/registrar")
    public Map<String, String> registrar(@Valid @RequestBody RegistroEntidad registroAlumno) {
          repo.save(registroAlumno);
          return Map.of("mensaje", "Registro guardado exitosamente");
    }
}
