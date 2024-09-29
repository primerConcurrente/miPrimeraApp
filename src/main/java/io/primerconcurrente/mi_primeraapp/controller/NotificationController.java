package io.primerconcurrente.mi_primeraapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/alert")
    @SendTo("/topic/notifications")
    public String sendAlert(String message) {
        return message;
    }
}
