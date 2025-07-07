package com.example.controller;

import com.example.service.MessageService;

public class UserController {
    private MessageService messageService;

    // setter-based DI
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void process() {
        messageService.sendMessage("Welcome to Spring Dependency Injection!");
    }
}

