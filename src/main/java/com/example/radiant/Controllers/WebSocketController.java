package com.example.radiant.Controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.radiant.Models.Mensaje;

@Controller
public class WebSocketController {
    @MessageMapping("send")
    @SendTo("/topic/messages")
    public Mensaje enviarMensaje(Mensaje mensaje) {
        mensaje.setLeido(false);
        return mensaje;
    }
}
