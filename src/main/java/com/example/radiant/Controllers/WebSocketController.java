package com.example.radiant.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.radiant.Models.Message;
import com.example.radiant.Service.MessageService;

@Controller
public class WebSocketController {

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService mensajeService;

    @MessageMapping("/send")
    public void enviarMensaje(Message mensaje) {
        mensajeService.saveMessage(mensaje);

        String destino = "/topic/mensajes/" + mensaje.getAddresseId();
        messagingTemplate.convertAndSend(destino, mensaje);
    }
}
