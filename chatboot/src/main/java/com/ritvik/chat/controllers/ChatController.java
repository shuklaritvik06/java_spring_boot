package com.ritvik.chat.controllers;

import com.ritvik.chat.entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message sendMessage(@RequestBody Message message){
        return message;
    }
}
