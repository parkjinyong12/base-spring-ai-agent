package com.example.agent.controller;

import com.example.agent.domain.AiChatOptions;
import com.example.agent.service.ChatOptionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class ChatOptionsController {

    private final ChatOptionsService chatOptionsService;

    public ChatOptionsController(ChatOptionsService chatOptionsService) {
        this.chatOptionsService = chatOptionsService;
    }

    @GetMapping
    public List<AiChatOptions> getAll() {
        return chatOptionsService.getAll();
    }

    @PutMapping("/{id}/default")
    public AiChatOptions setDefault(@PathVariable Long id) {
        return chatOptionsService.setDefault(id);
    }
}
