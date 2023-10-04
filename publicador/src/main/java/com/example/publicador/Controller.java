package com.example.publicador;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

@RestController
public class Controller {
    private final Servicio servicio;

    public Controller(Servicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping("public/direct/{message}")
    public String publishDirectMessage(@PathVariable String message){
        servicio.publish(message);
        return message;

    }
    @PostMapping("public/topic/{message}")
    public String publishTopicMessage(@PathVariable String message){
        servicio.publishTopic(message);
        return message;

    }

    @PostMapping("public/fanout/{message}")
    public String publishFanoutMessage(@PathVariable String message){
        servicio.publishFonout(message);
        return message;

    }
}
